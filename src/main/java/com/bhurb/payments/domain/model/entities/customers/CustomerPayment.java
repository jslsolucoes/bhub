package com.bhurb.payments.domain.model.entities.customers;

import com.bhurb.payments.domain.model.entities.payments.Payment;
import com.bhurb.payments.domain.model.valueobject.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class CustomerPayment {

    @Id
    @GeneratedValue
    private final Long id;

    @NotNull
    private final Long refId;

    private final String name;

    @Embedded
    private final Email email;

    @OneToOne
    @NotNull
    private final Payment payment;

    @Deprecated
    public CustomerPayment() {
        this(null, null, null, null, null);
    }

    public CustomerPayment(final Long id,
                           final Long refId,
                           final String name,
                           final Email email,
                           final Payment payment) {
        this.id = id;
        this.refId = refId;
        this.name = name;
        this.email = email;
        this.payment = payment;
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

    public CustomerPayment withPayment(final Payment payment) {
        return new CustomerPayment(id, refId, name, email, payment);
    }
}
