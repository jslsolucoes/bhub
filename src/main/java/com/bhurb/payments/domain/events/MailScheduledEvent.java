package com.bhurb.payments.domain.events;

import org.springframework.context.ApplicationEvent;

public class MailScheduledEvent extends ApplicationEvent
        implements DomainEvent<MailScheduledEvent.MailCreatedEventDetails> {

    public MailScheduledEvent(final String to, final String subject, final String body) {
        super(new MailCreatedEventDetails(to, subject, body));
    }

    @Override
    public MailCreatedEventDetails source() {
        return (MailCreatedEventDetails) getSource();
    }

    public record MailCreatedEventDetails(String to, String subject, String body) {
    }
}
