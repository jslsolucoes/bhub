package com.bhurb.payments.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Entity
public class BookPayment extends ProductPayment {

    @NotNull
    private final Long refId;

    @NotBlank
    private final String name;

    @NotBlank
    private final String author;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private final BookType type;

    @Deprecated
    public BookPayment() {
        this(null, null, null, null, null, null);
    }

    public BookPayment(final Long id,
                       final Long refId,
                       final String name,
                       final String author,
                       final BookType type,
                       final Payment payment) {
        super(id, payment);
        this.refId = refId;
        this.name = name;
        this.author = author;
        this.type = type;
    }

    @Override
    public BookPayment withPayment(final Payment payment) {
        return new BookPayment(id, refId, name, author, type, payment);
    }

    @Override
    public Optional<DeliveryDocItem> asDeliveryDocItem() {
        return Optional.of(
                new DeliveryDocItem(null, refId, name, false, null)
        );
    }

    @Override
    public boolean isBook() {
        return true;
    }

    @Override
    public boolean isPhysical() {
        return type.isPhysical();
    }

    @Override
    public String toString() {
        return "BookPayment{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", type=" + type +
                '}';
    }

    public enum BookType {
        DIGITAL, PHYSICAL, BOTH;

        public boolean isPhysical() {
            return this == PHYSICAL || this == BOTH;
        }

    }
}
