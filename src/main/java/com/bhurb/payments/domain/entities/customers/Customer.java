package com.bhurb.payments.domain.entities.customers;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private final Long id;

    @Embedded
    private final Email email;

    public Customer(final Long id, final Email email) {
        this.id = id;
        this.email = email;
    }

    public Customer() {
        this(null, null);
    }

    public Long id() {
        return id;
    }

    public Email email() {
        return email;
    }

}
