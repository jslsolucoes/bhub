package com.bhurb.payments.domain.model.entities;

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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private final CustomerPayment customerPayment;

    @NotNull
    private final LocalDateTime createdAt;

    @NotNull
    private final BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private final ProductPayment product;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private final SellerPayment sellerPayment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private Comission commission;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "payment", fetch = FetchType.LAZY, optional = false)
    private DeliveryDoc deliveryDoc;

    @Deprecated
    public Payment() {
        this(null, null, null,
                null, null, null);
    }

    public Payment(final Long id,
                   final LocalDateTime createdAt,
                   final CustomerPayment customerPayment,
                   final BigDecimal amount,
                   final ProductPayment product,
                   final SellerPayment sellerPayment) {

        this.id = id;
        this.createdAt = createdAt;
        this.amount = amount;
        this.customerPayment = Optional.ofNullable(customerPayment)
                .map(cpy -> cpy.withPayment(this))
                .orElse(null);
        this.product = Optional.ofNullable(product)
                .map(pdr -> pdr.withPayment(this))
                .orElse(null);
        this.sellerPayment = Optional.ofNullable(sellerPayment)
                .map(sll -> sll.withPayment(this))
                .orElse(null);
    }

    public void addComission(final Comission comission) {
        this.commission = comission;
    }

    public Payment createNewDeliveryDoc() {
        this.deliveryDoc = new DeliveryDoc(null, this);
        return this;
    }

    public Payment addItemToDeliveryDoc(final DeliveryDocItem deliveryDocItem) {
        this.deliveryDoc.addItem(deliveryDocItem);
        return this;
    }

    public Long id() {
        return id;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public CustomerPayment customer() {
        return customerPayment;
    }

    public BigDecimal amount() {
        return amount;
    }

    public ProductPayment product() {
        return product;
    }

    public SellerPayment seller() {
        return sellerPayment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customer=" + customerPayment +
                ", createdAt=" + createdAt +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }


}
