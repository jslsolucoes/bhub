package com.bhurb.payments.domain.model.entities.customers;

import com.bhurb.payments.domain.model.valueobject.Email;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private final Long id;

    private final String name;

    @Embedded
    private final Email email;

    @Deprecated
    public Customer() {
        this(null, null, null);
    }

    public Customer(final Long id,
                    final String name,
                    final Email email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long id() {
        return id;
    }

    public Email email() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                '}';
    }
}
