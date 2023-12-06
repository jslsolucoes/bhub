package com.bhurb.payments.domain.events;

public interface DomainEvent<T> {
    T source();
}
