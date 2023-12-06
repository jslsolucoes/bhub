package com.bhurb.payments.application.payments;


import com.bhurb.payments.domain.model.entities.payments.specs.*;
import com.bhurb.payments.domain.model.entities.products.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentProcessor {



    @Autowired
    public PaymentProcessor(List<PaymentSpec> specs) {

    }

    public PaymentProcessorOutput process(final PaymentProcessorInput paymentProcessorInput) {

        var book = new Book(1L, "Intensive data structures and algorithms", "Some author", Book.BookType.DIGITAL);

        var isBookSpec = new IsBookSpec();
        var isPhysicalSpec = new IsPhysicalSpec();
        var isBookOrIsPhysical = Spec.or(isBookSpec, isPhysicalSpec);
        var isNewMembership = new IsNewMembershipSpec();


        var isBook = isBookSpec.isSatisfiedBy(book);
        var isPhysicalBook = isPhysicalSpec.isSatisfiedBy(book);
        var isBookOrIsPhysicalBook = isBookOrIsPhysical.isSatisfiedBy(book);





        return new PaymentProcessorOutput(paymentProcessorInput.productId());
    }


    public record PaymentProcessorInput(long productId) {

    }

    public record PaymentProcessorOutput(long productId) {

    }
}
