package com.example.mapper;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.domain.UserActivityEvent;
import com.example.domain.EventType;
import com.example.domain.builder.EventBuilder;


public class JSONSerailiserTest {

  private static final Logger LOG = LoggerFactory.getLogger(JSONSerailiserTest.class);

  public static final String CONSUMER_REFERENCE = "qtvszw9qbjnswfprsxnfelk2yvdvqt09";
  public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:39.0) Gecko/20100101 Firefox/39.0";

  private JSONSerailiser jsonSerailiser;

  private EventBuilder eventBuilder = new EventBuilder();

  private UserActivityEvent userActivityEvent;

  @Before
  public void setUp() throws Exception {

    jsonSerailiser = new JSONSerailiser();

    final LocalDateTime dateTime = LocalDateTime.of(2016, 2, 29, 23, 45, 50, 40);

    userActivityEvent = eventBuilder.withId(1l)
        .withEventType(EventType.LOGIN_INTERCEPTOR_CREATE_SESSION)
        .withClientIp("198.39.100.45")
        .withCorrelationId("2c08b8f1-afbb-455a-a1bd-31f15115d424")
        .withDate(dateTime)
        .withDetails("Piet just logged in!")
        .withReference(CONSUMER_REFERENCE)
        .withUserAgent(USER_AGENT)
        .withUserAgentFiltered("FIREFOX39-MAC_OS_X")
        .createEvent();
  }

  @After
  public void tearDown() throws Exception {
    userActivityEvent = null;
    jsonSerailiser = null;
  }

  @Test
  public void testConvert() throws Exception {
    final String json = jsonSerailiser.convert(userActivityEvent);
    LOG.info("UserActivityEvent object converted into JSON: {} ",json);
    assertThat(json, allOf( notNullValue(),
        startsWith("{\"id\":1,\"eventType\":\"LOGIN_INTERCEPTOR_CREATE_SESSION\""),
        containsString("\"date\":[2016,2,29,23,45,50,40]"),
        endsWith("\"reference\":\"qtvszw9qbjnswfprsxnfelk2yvdvqt09\"}")
    ) );
  }

}