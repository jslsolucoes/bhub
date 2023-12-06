package com.bhurb.payments.domain.events;

import com.bhurb.payments.domain.notifications.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MailNotificationListener {

    private final MailNotification mailNotification;

    @Autowired
    public MailNotificationListener(final MailNotification mailNotification) {
        this.mailNotification = mailNotification;
    }

    @EventListener
    public void on(final MailCreatedEvent mailCreatedEvent) {
        var eventDetails = mailCreatedEvent.source();
        var to = eventDetails.to();
        var subject = eventDetails.subject();
        var body = eventDetails.body();
        this.mailNotification.send(to, subject, body);
    }


}
