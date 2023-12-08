package com.bhurb.payments.domain.model.entities;

import java.time.LocalDateTime;

public class PaymentBuilder {

    private LocalDateTime createdAt;
    private ProductPayment productPayment;

    private CustomerPayment customerPayment;

    private PaymentBuilder() {
        createdAt = LocalDateTime.now();
    }

    public static PaymentBuilder create() {
        return new PaymentBuilder();
    }

    public Payment build() {
        return new Payment(
                null,
                createdAt,
                customerPayment,
                null,
                productPayment,
                null
        );
    }

    public PaymentBuilder withBook(final Long id,
                                   final String name,
                                   final String author,
                                   final BookPayment.BookType bookType) {
        this.productPayment = new BookPayment(null, id, name, author, bookType, null);
        return this;
    }

    public PaymentBuilder withCustomerWithoutMembership() {
        this.customerPayment = new CustomerPayment(
                null,
                null,
                null,
                null,
                null,
                null
        );
        return this;
    }

    public PaymentBuilder withCustomerWithMembership(final MembershipPayment.MembershipPlan membershipPlan) {
        this.customerPayment = new CustomerPayment(
                null,
                null,
                null,
                null,
                membershipPlan,
                null
        );
        return this;
    }
}
