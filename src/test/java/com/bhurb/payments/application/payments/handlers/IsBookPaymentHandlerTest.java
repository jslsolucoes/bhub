package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.model.entities.PaymentBuilder;
import com.bhurb.payments.domain.model.entities.BookPayment;
import com.bhurb.payments.junit.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class IsBookPaymentHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private IsBookPaymentHandler isBookPaymentHandler;

    @MockBean
    private PaymentHandlerChain paymentHandlerChain;

    @Test
    void shouldDetectIsBook() {
        var payment = PaymentBuilder.create()
                .withBook(1L, "The Lord of the Rings", "J. R. R. Tolkien", BookPayment.BookType.DIGITAL)
                .build();
        var paymentHandlerContext = new PaymentHandlerContext(payment);
        isBookPaymentHandler.doNext(paymentHandlerContext, paymentHandlerChain);
        verify(paymentHandlerChain).next();
    }

}