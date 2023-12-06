package com.bhurb.payments.domain.entities;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private final Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private final Product product;

    @ManyToOne
    private final Customer customer;

    @Deprecated
    public Payment() {
        this(null, null, null);
    }

    public Payment(final Long id,
                   final Product product,
                   final Customer customer) {
        this.id = id;
        this.product = Optional.ofNullable(product)
                .map(prod -> prod.withPayment(this))
                .orElse(null);
        this.customer = customer;
    }

    public Long id() {
        return id;
    }

    public Product product() {
        return product;
    }

}
