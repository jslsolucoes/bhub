package com.bhurb.payments.domain.events;

import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewMembershipListener {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NewMembershipListener.class);

    @EventListener
    public void on(final NewMembershipEvent newMembershipEvent) {
        var details = newMembershipEvent.source();
        LOGGER.debug("New membership event received {}", details);
    }

}
