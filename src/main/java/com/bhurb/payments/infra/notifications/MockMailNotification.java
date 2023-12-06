package com.bhurb.payments.infra.notifications;

import com.bhurb.payments.domain.notifications.MailNotification;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MockMailNotification implements MailNotification {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MockMailNotification.class);

    @Override
    public void send(String to, String subject, String body) {
        LOGGER.info("Sending email to {} with subject {} and body {}", to, subject, body);
    }
}
