package com.bhurb.payments.domain.entities;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product {

    private final String author;

    @Deprecated
    public Book() {
        this(null, null, null, null);
    }

    public Book(final Long id,
                final String name,
                final String author,
                final Payment payment) {
        super(id, name, payment);
        this.author = author;
    }

    @Override
    public Book withPayment(final Payment payment) {
        return new Book(id, name, author, payment);
    }
}
