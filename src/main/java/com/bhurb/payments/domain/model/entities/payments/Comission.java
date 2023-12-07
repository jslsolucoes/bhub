package com.bhurb.payments.domain.model.entities.payments;

import jakarta.persistence.*;

@Entity
public class Comission {

    @Id
    @GeneratedValue
    private final Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private final Seller seller;

    @OneToOne
    private final Payment payment;

    @Deprecated
    public Comission() {
        this(null, null, null);
    }

    public Comission(final Long id,
                     final Seller seller,
                     final Payment payment) {
        this.id = id;
        this.seller = seller;
        this.payment = payment;
    }

    public Long id() {
        return id;
    }

    public Seller seller() {
        return seller;
    }
}
