package com.example.domain.builder;

import java.time.LocalDateTime;

import com.example.domain.UserActivityEvent;
import com.example.domain.EventType;

public class EventBuilder {
  private Long id;
  private EventType eventType;
  private LocalDateTime date;
  private String correlationId;
  private String clientIp;
  private String userAgent;
  private String userAgentFiltered;
  private String details;
  private String reference;

  public EventBuilder withId(final Long id) {
    this.id = id;
    return this;
  }

    public EventBuilder withEventType(final EventType eventType) {
    this.eventType = eventType;
    return this;
  }

  public EventBuilder withDate(final LocalDateTime date) {
    this.date = date;
    return this;
  }

  public EventBuilder withCorrelationId(final String correlationId) {
    this.correlationId = correlationId;
    return this;
  }

  public EventBuilder withClientIp(final String clientIp) {
    this.clientIp = clientIp;
    return this;
  }

  public EventBuilder withUserAgent(final String userAgent) {
    this.userAgent = userAgent;
    return this;
  }

  public EventBuilder withUserAgentFiltered(final String userAgentFiltered) {
    this.userAgentFiltered = userAgentFiltered;
    return this;
  }

  public EventBuilder withDetails(final String details) {
    this.details = details;
    return this;
  }

  public EventBuilder withReference(final String reference) {
    this.reference = reference;
    return this;
  }

  public UserActivityEvent createEvent() {
    return new UserActivityEvent(id, eventType, date, correlationId, clientIp, userAgent,
        userAgentFiltered, details, reference);
  }
}