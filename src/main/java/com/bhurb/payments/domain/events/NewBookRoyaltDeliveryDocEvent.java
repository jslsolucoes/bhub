package com.bhurb.payments.domain.events;

import com.bhurb.payments.domain.model.entities.DeliveryDoc;
import org.springframework.context.ApplicationEvent;

public class NewBookRoyaltDeliveryDocEvent extends ApplicationEvent
        implements DomainEvent<NewBookRoyaltDeliveryDocEvent.NewBookRoyaltDeliveryDocEventDetails> {

    public NewBookRoyaltDeliveryDocEvent(final DeliveryDoc deliveryDoc) {
        super(new NewBookRoyaltDeliveryDocEventDetails(deliveryDoc));
    }

    @Override
    public NewBookRoyaltDeliveryDocEventDetails source() {
        return (NewBookRoyaltDeliveryDocEventDetails) getSource();
    }

    public record NewBookRoyaltDeliveryDocEventDetails(DeliveryDoc deliveryDoc) {
    }
}
