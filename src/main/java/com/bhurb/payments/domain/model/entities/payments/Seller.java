package com.bhurb.payments.domain.model.entities.payments;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Seller {


    @Id
    @GeneratedValue
    private final Long id;

    private String name;

    @Deprecated
    public Seller() {
        this(null, null);
    }

    public Seller(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }
}
