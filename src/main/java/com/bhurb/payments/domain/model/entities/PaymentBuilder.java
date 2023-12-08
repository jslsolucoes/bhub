package com.bhurb.payments.domain.model.entities;

import com.bhurb.payments.domain.model.valueobject.Email;

import java.time.LocalDateTime;

public class PaymentBuilder {

    private LocalDateTime createdAt;
    private ProductPayment productPayment;
    private CustomerPayment customerPayment;
    private DeliveryDoc deliveryDoc;

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
                null,
                deliveryDoc
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
                new Email("aa@aa.com"),
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

    public PaymentBuilder withDeliveryDoc() {
        this.deliveryDoc = new DeliveryDoc();
        return this;
    }

    public PaymentBuilder withVideo(final Long id,
                                    final String name) {
        this.productPayment = new VideoPayment(null, id, name, null);
        return this;
    }

    public PaymentBuilder withMembership(final Long id,
                                         final MembershipPayment.MembershipPlan membershipPlan) {
        this.productPayment = new MembershipPayment(null, id, membershipPlan, null);
        return this;
    }
}
