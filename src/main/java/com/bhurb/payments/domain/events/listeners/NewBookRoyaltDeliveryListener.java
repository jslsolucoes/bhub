package com.bhurb.payments.domain.events.listeners;

import com.bhurb.payments.domain.events.NewBookRoyaltDeliveryDocEvent;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewBookRoyaltDeliveryListener {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NewBookRoyaltDeliveryListener.class);

    @EventListener
    public void on(final NewBookRoyaltDeliveryDocEvent newBookRoyaltDeliveryDocEvent) {
        var details = newBookRoyaltDeliveryDocEvent.source();
        LOGGER.debug("Push delivery doc copy to royalt departure {}", details);
    }

}
