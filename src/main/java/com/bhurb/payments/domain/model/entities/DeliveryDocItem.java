package com.bhurb.payments.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DeliveryDocItem {

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

    public DeliveryDocItem withDeliveryDoc(final DeliveryDoc deliveryDoc) {
        return new DeliveryDocItem(id, refId, name, isForFree, deliveryDoc);
    }

    public DeliveryDocItem() {
        this(null, null, null, null, null);
    }
}
