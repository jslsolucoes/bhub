package com.bhurb.payments.domain.entities.products;

import com.bhurb.payments.domain.entities.payments.specs.PaymentSpec;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book implements PaymentSpec {

    @Id
    @NotNull
    @GeneratedValue
    protected final Long id;

    @NotNull
    protected final String name;

    private final String author;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private final BookType type;

    @Deprecated
    public Book() {
        this(null, null, null, null);
    }


    public Book(final Long id,
                final String name,
                final String author,
                final BookType type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
    }

    @Override
    public boolean isBook() {
        return true;
    }

    @Override
    public boolean isPhysical() {
        return type.isPhysical();
    }

    enum BookType {
        DIGITAL, PHYSICAL, BOTH;

        public boolean isPhysical() {
            return this == PHYSICAL || this == BOTH;
        }

    }
}
