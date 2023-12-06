package com.bhurb.payments.domain.events;

public interface PaymentEvent<T> {
    T source();
}
