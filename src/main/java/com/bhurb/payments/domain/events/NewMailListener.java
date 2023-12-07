package com.bhurb.payments.domain.events;

import com.bhurb.payments.domain.notifications.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewMailListener {

    private final MailNotification mailNotification;

    @Autowired
    public NewMailListener(final MailNotification mailNotification) {
        this.mailNotification = mailNotification;
    }

    @EventListener
    public void on(final NewMailEvent newMailEvent) {
        var eventDetails = newMailEvent.source();
        var to = eventDetails.to();
        var subject = eventDetails.subject();
        var body = eventDetails.body();
        this.mailNotification.send(to, subject, body);
    }


}
