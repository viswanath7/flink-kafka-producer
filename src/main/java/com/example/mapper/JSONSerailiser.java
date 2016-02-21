package com.example.mapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JSONSerailiser {

  private static final Logger LOG = LoggerFactory.getLogger(JSONSerailiser.class);

  private final ObjectMapper mapper = new ObjectMapper();

  public JSONSerailiser() {
    mapper.registerModule(new JavaTimeModule());
  }

  public <T> String convert(T obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (final JsonProcessingException jsonEx) {
      LOG.error("Error encountered while trying to convert: \n {} \n to JSON.", jsonEx);
      return "";
    }
  }

}
