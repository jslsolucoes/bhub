package com.bhurb.payments.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DeliveryDoc {

    @Id
    @GeneratedValue
    private final Long id;

    @OneToOne
    @NotNull
    private final Payment payment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "deliveryDoc", fetch = FetchType.LAZY)
    private final Set<DeliveryDocItem> items;

    @Deprecated
    public DeliveryDoc() {
        this(null, null);
    }

    public DeliveryDoc(final Long id,
                       final Payment payment) {
        this.id = id;
        this.payment = payment;
        this.items = new HashSet<>();
    }

    public DeliveryDoc addItem(final DeliveryDocItem item) {
        items.add(item.withDeliveryDoc(this));
        return this;
    }

}
