package com.bhurb.payments.domain.model.entities.payments;

import com.bhurb.payments.domain.model.entities.products.BookPayment;
import com.bhurb.payments.domain.model.entities.products.ProductPayment;

import java.time.LocalDateTime;

public class PaymentBuilder {

    private LocalDateTime createdAt;
    private ProductPayment productPayment;

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
                null,
                null,
                productPayment,
                null
        );
    }

    public PaymentBuilder withBook(final String name,
                                   final String author,
                                   final BookPayment.BookType bookType) {
        this.productPayment = new BookPayment(null, name, author, bookType, null);
        return this;
    }
}
