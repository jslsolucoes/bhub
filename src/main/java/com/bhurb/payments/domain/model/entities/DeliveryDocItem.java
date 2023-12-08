package com.bhurb.payments.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DeliveryDocItem implements Cloneable {

    @Id
    @GeneratedValue
    private final Long id;

    private final Long refId;

    private final String name;

    private final Boolean isForFree;

    @ManyToOne
    private final DeliveryDoc deliveryDoc;

    public DeliveryDocItem(final Long id,
                           final Long refId,
                           final String name,
                           final Boolean isForFree,
                           final DeliveryDoc deliveryDoc) {
        this.id = id;
        this.refId = refId;
        this.name = name;
        this.deliveryDoc = deliveryDoc;
        this.isForFree = isForFree;
    }

    public DeliveryDocItem() {
        this(null, null, null, null, null);
    }

    public String name() {
        return name;
    }

    public DeliveryDocItem withDeliveryDoc(final DeliveryDoc deliveryDoc) {
        return new DeliveryDocItem(id, refId, name, isForFree, deliveryDoc);
    }

    @Override
    public DeliveryDocItem clone() {
        return new DeliveryDocItem(null, refId, name, isForFree, deliveryDoc);
    }
}
