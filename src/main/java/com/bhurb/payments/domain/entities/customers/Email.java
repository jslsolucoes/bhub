package com.bhurb.payments.domain.entities.customers;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Column(name = "email")
    private final String address;

    public Email(final String address) {
        if (address == null || !address.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.address = address;
    }

    @Deprecated
    public Email() {
        this(null);
    }

    public String address() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var email = (Email) o;
        return address.equals(email.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }
}
