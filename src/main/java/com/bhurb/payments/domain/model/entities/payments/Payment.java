package com.bhurb.payments.domain.model.entities.payments;

import com.bhurb.payments.domain.model.entities.customers.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private final Long id;
    @ManyToOne
    private final Customer customer;

    @Deprecated
    public Payment() {
        this(null, null);
    }

    public Payment(final Long id,
                   final Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public Long id() {
        return id;
    }


}
