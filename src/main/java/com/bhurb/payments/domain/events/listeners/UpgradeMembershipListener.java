package com.bhurb.payments.domain.events.listeners;

import com.bhurb.payments.domain.events.UpgradeMembershipEvent;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UpgradeMembershipListener {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UpgradeMembershipListener.class);

    @EventListener
    public void on(final UpgradeMembershipEvent upgradeMembershipEvent) {
        var details = upgradeMembershipEvent.source();
        LOGGER.debug("Upgrade membership event received {}", details);
    }

}
