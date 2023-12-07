package com.bhurb.payments.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comission {

    @Id
    @GeneratedValue
    private final Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private final SellerPayment sellerPayment;

    @OneToOne
    @NotNull
    private final Payment payment;

    @Deprecated
    public Comission() {
        this(null, null, null);
    }

    public Comission(final Long id,
                     final SellerPayment sellerPayment,
                     final Payment payment) {
        this.id = id;
        this.sellerPayment = sellerPayment;
        this.payment = payment;
    }

    public Long id() {
        return id;
    }

    public SellerPayment seller() {
        return sellerPayment;
    }
}
