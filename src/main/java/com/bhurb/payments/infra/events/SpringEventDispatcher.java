package com.bhurb.payments.infra.events;

import com.bhurb.payments.domain.events.EventDispatcher;
import com.bhurb.payments.domain.events.MailScheduledEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringEventDispatcher implements EventDispatcher {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringEventDispatcher(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void scheduleMail(String to, String subject, String body) {
        this.applicationContext.publishEvent(new MailScheduledEvent(to, subject, body));
    }
}
