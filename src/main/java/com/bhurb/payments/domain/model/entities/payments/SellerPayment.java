package com.bhurb.payments.domain.model.entities.payments;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class SellerPayment {


    @Id
    @GeneratedValue
    private final Long id;

    @NotNull
    private final Long refId;

    private String name;

    @OneToOne
    @NotNull
    private final Payment payment;

    @Deprecated
    public SellerPayment() {
        this(null, null, null, null);
    }

    public SellerPayment(final Long id,
                         final Long refId,
                         final String name,
                         final Payment payment) {
        this.id = id;
        this.refId = refId;
        this.name = name;
        this.payment = payment;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public SellerPayment withPayment(final Payment payment) {
        return new SellerPayment(id, refId, name, payment);
    }
}
