package com.bhurb.payments.domain.events;

public interface EventDispatcher {
    void sendMail(final String to, final String subject, final String body);
}
