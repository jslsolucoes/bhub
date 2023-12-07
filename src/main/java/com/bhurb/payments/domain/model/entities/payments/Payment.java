package com.bhurb.payments.domain.model.entities.payments;

import com.bhurb.payments.domain.model.entities.customers.Customer;
import com.bhurb.payments.domain.model.entities.products.ProductPayment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private final Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private final Customer customer;

    @NotNull
    private final LocalDateTime createdAt;

    @NotNull
    private final BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private final ProductPayment product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private final Seller seller;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private Comission commission;

    @Deprecated
    public Payment() {
        this(null, null, null,
                null, null, null);
    }

    public Payment(final Long id,
                   final LocalDateTime createdAt,
                   final Customer customer,
                   final BigDecimal amount,
                   final ProductPayment product,
                   final Seller seller) {
        this.id = id;
        this.createdAt = createdAt;
        this.customer = customer;
        this.amount = amount;
        this.product = Optional.ofNullable(product)
                .map(pdr -> pdr.withPayment(this))
                .orElse(null);
        this.seller = seller;
    }

    public void addComission(final Comission comission) {
        this.commission = comission;
    }

    public Long id() {
        return id;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public Customer customer() {
        return customer;
    }

    public BigDecimal amount() {
        return amount;
    }

    public ProductPayment product() {
        return product;
    }

    public Seller seller() {
        return seller;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customer=" + customer +
                ", createdAt=" + createdAt +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }


}
