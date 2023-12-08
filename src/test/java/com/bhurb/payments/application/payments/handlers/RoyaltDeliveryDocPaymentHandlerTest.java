package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.events.EventDispatcher;
import com.bhurb.payments.domain.model.entities.BookPayment;
import com.bhurb.payments.domain.model.entities.PaymentBuilder;
import com.bhurb.payments.junit.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class RoyaltDeliveryDocPaymentHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private RoyaltDeliveryDocPaymentHandler royaltDeliveryDocPaymentHandler;

    @MockBean
    private PaymentHandlerContext paymentHandlerContext;

    @MockBean
    private PaymentHandlerChain paymentHandlerChain;

    @MockBean
    private EventDispatcher eventDispatcher;

    @Test
    @DisplayName("Should generate a Royalt Delivery Doc")
    void shouldGenerateRoyaltDeliveryDoc() {

        var payment = PaymentBuilder.create()
                .withBook(1L, "The Lord of the Rings", "J. R. R. Tolkien", BookPayment.BookType.BOTH)
                .withDeliveryDoc()
                .build();
        when(paymentHandlerContext.payment())
                .thenReturn(payment);
        royaltDeliveryDocPaymentHandler.doNext(paymentHandlerContext, paymentHandlerChain);
        verify(paymentHandlerChain).next();
        verify(eventDispatcher).newBookRoyaltDeliveryDoc(any());
    }

}