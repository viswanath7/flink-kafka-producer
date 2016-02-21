package com.example;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.domain.UserActivityEvent;
import com.example.mapper.JSONSerailiser;
import com.example.domain.EventType;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

/**
 * Publisher for writing data into Kafka.
 *
 * The following arguments are required:
 *
 *  - "bootstrap.servers" (comma separated list of kafka brokers)
 *  - "topic" the name of the topic to write data to.
 *
 * This is an example command line argument:
 *  "--topic test --bootstrap.servers localhost:9092"
 */
public class KafkaProducer {

  private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

  private final static InetAddressValidator inetAddressValidator = new InetAddressValidator();

  public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private static StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

  private static JSONSerailiser jsonSerailiser = new JSONSerailiser();

	public static void main(String[] args) throws Exception {
		// parse user parameters
		ParameterTool parameterTool = ParameterTool.fromArgs(args);

		// Add a simple source for the data stream which continually writes some generated strings
		DataStream<String> messageStream = executionEnvironment.addSource(new SimpleStringGenerator());

		// write contents of data stream into Kafka
		messageStream.addSink(
				new FlinkKafkaProducer<>(parameterTool.getRequired("bootstrap.servers"),
						parameterTool.getRequired("topic"),
						new SimpleStringSchema()));

		executionEnvironment.execute();
	}

	public static class SimpleStringGenerator implements SourceFunction<String> {
		private static final long serialVersionUID = 2174904787118597072L;
		boolean running = true;
		// long i = 0;
		@Override
		public void run(SourceContext<String> ctx) throws Exception {
      CSVReader reader = new CSVReader(new FileReader("src/main/resources/dataset/events.csv"), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 1);

      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        final UserActivityEvent userActivityEvent =  createFrom(nextLine);
        ctx.collect(jsonSerailiser.convert(userActivityEvent));
        Thread.sleep(1000); // sleep for 1000 ms to avoid polluting the topic too much too fast
        //create json representation of events from file
      }
		}

		@Override
		public void cancel() {
			running = false;
		}
	}

  public static UserActivityEvent createFrom(final String[] fields) {

    if(fields==null || fields.length<9) {
      LOG.warn("Malformed line. It does NOT have 9 fields so skipping it.");
      return new UserActivityEvent();
    }

    final Long eventId = Long.parseLong(fields[0]);

    final String clientIp = fields[1];
    if(!inetAddressValidator.isValid(clientIp)) {
      LOG.warn("Validation of client's ip address: {} failed", clientIp);
    }

    final String correlationIdentifier = fields[2];

    final LocalDateTime eventDateTime = LocalDateTime.parse(fields[3], formatter);

    final String details = fields[4];
    final EventType eventType = EventType.valueOf(fields[5]);
    final String userAgent = fields[6];
    final String userAgentFiltered = fields[7];
    final String reference = fields[8];

    return new UserActivityEvent(eventId, eventType, eventDateTime, correlationIdentifier, clientIp, userAgent,
        userAgentFiltered, details, reference);
  }

	public static class SimpleStringSchema implements DeserializationSchema<String>, SerializationSchema<String, byte[]> {
		private static final long serialVersionUID = 1L;

		public SimpleStringSchema() {
		}

		public String deserialize(byte[] message) {
			return new String(message);
		}

		public boolean isEndOfStream(String nextElement) {
			return false;
		}

		public byte[] serialize(String element) {
			return element.getBytes();
		}

		public TypeInformation<String> getProducedType() {
			return TypeExtractor.getForClass(String.class);
		}

	}
}
