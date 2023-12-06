package com.bhurb.payments.domain.notifications;

public interface MailNotification {

    void send(final String to, final String subject, final String body);
}
