package com.bhurb.payments.domain.events;

import com.bhurb.payments.domain.notifications.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MailSchedulerListener {

    private final MailNotification mailNotification;

    @Autowired
    public MailSchedulerListener(final MailNotification mailNotification) {
        this.mailNotification = mailNotification;
    }

    @EventListener
    public void on(final MailScheduledEvent mailScheduledEvent) {
        var eventDetails = mailScheduledEvent.source();
        var to = eventDetails.to();
        var subject = eventDetails.subject();
        var body = eventDetails.body();
        this.mailNotification.send(to, subject, body);
    }


}
