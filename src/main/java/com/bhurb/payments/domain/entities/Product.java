package com.bhurb.payments.domain.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = jakarta.persistence.InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    @NotNull
    @GeneratedValue
    protected final Long id;

    @NotNull
    protected final String name;

    @OneToOne
    protected final Payment payment;

    public Product() {
        this(null, null, null);
    }

    public Product(final Long id,
                   final String name,
                   final Payment payment) {
        this.id = id;
        this.name = name;
        this.payment = payment;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public abstract Product withPayment(final Payment payment);
}
