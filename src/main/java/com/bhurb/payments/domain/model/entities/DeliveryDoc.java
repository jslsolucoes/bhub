package com.bhurb.payments.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class DeliveryDoc implements Cloneable {

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
        this(null, null, null);
    }

    public DeliveryDoc(final Long id,
                       final Set<DeliveryDocItem> items,
                       final Payment payment) {
        this.id = id;
        this.payment = payment;
        this.items = Optional.ofNullable(items)
                .orElse(new HashSet<>())
                .stream()
                .map(item -> item.withDeliveryDoc(this))
                .collect(Collectors.toSet());
    }

    public DeliveryDoc addItem(final DeliveryDocItem item) {
        items.add(item.withDeliveryDoc(this));
        return this;
    }

    public DeliveryDoc withPayment(final Payment payment) {
        return new DeliveryDoc(id, items, payment);
    }

    @Override
    public DeliveryDoc clone() {
        var clonedItems = this.items
                .stream()
                .map(DeliveryDocItem::clone)
                .collect(Collectors.toSet());
        return new DeliveryDoc(id, clonedItems, payment);
    }

    public Set<DeliveryDocItem> items() {
        return items;
    }
}
