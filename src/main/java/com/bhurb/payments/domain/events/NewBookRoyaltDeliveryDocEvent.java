package com.bhurb.payments.domain.events;

import org.springframework.context.ApplicationEvent;

public class NewBookRoyaltDeliveryDocEvent extends ApplicationEvent
        implements DomainEvent<NewBookRoyaltDeliveryDocEvent.NewBookRoyaltDeliveryDocEventDetails> {

    public NewBookRoyaltDeliveryDocEvent(final Long bookId, final String author) {
        super(new NewBookRoyaltDeliveryDocEventDetails(bookId, author));
    }

    @Override
    public NewBookRoyaltDeliveryDocEventDetails source() {
        return (NewBookRoyaltDeliveryDocEventDetails) getSource();
    }

    public record NewBookRoyaltDeliveryDocEventDetails(Long bookId, String author) {
    }
}
