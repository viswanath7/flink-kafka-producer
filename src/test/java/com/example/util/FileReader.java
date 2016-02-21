package com.example.util;


import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileReader {

  private static final Logger LOG = LoggerFactory.getLogger(FileReader.class);

  private FileReader() {
    throw new UnsupportedOperationException("Invocation of private constructor is disallowed!");
  }

  /**
   * Retrieves the content of a file without new line characters between lines
   *
   * @param fileName
   * @return
   */
  public static String getFileContent(final String fileName) {
    try {
      final List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
      return StreamSupport.stream(lines.spliterator(), false)
          .reduce("", (x, y) -> x + y); // Concatenate lines with "" as initial value
    } catch (final IOException ioEx) {
      LOG.error("Error encountered while trying to read the file {}", ioEx);
      return "";
    }
  }


  /**
   * Collector to merge all characters of the stream into a single string
   *
   * @return
   */
  public static  Collector<Character, StringJoiner, String> getCharacterStreamStringCollector() {
    return Collector.of(
        () -> new StringJoiner(""),                                 // supplier with empty string as delimiter
        (stringJoiner, character) ->
            stringJoiner.add(CharBuffer.wrap(new char[]{character})),   // accumulator adds each character to string joiner helper class
        StringJoiner::merge,                                   // combiner merges two string joiners into one
        StringJoiner::toString);
  }

  /**
   * Print all non white space characters in a string onto the terminal
   *
   * @param input
   */
  public static void printNonWhiteSpaceCharacters(final String input) {
    final String output = input.chars()
        .mapToObj(i -> (char) i)
        .filter(character -> !Character.isWhitespace(character))
        .collect(getCharacterStreamStringCollector());
  }



}
