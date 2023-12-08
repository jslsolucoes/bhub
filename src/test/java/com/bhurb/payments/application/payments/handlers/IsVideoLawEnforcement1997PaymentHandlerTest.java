package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.model.entities.PaymentBuilder;
import com.bhurb.payments.junit.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class IsVideoLawEnforcement1997PaymentHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private IsVideoLawEnforcement1997PaymentHandler isVideoLawEnforcement1997PaymentHandler;

    @MockBean
    private PaymentHandlerContext paymentHandlerContext;

    @MockBean
    private PaymentHandlerChain paymentHandlerChain;

    @Test
    @DisplayName("Should add free video to delivery doc")
    void shouldAddFreeVideoToDeliveryDoc() {
        var payment = PaymentBuilder.create()
                .withDeliveryDoc()
                .withVideo(157L, "Aprendendo a Esquiar")
                .build();
        when(paymentHandlerContext.payment())
                .thenReturn(payment);
        isVideoLawEnforcement1997PaymentHandler.doNext(paymentHandlerContext, paymentHandlerChain);
        verify(paymentHandlerChain).next();
        var containPrimeirosSocorros = payment.deliveryDoc()
                .items()
                .stream()
                .anyMatch(deliveryDocItem -> "Primeiros Socorros".equalsIgnoreCase(deliveryDocItem.name()));
        assertTrue(containPrimeirosSocorros);
    }

    @Test
    @DisplayName("Should not add free video to delivery doc")
    void shouldNotAddFreeVideoToDeliveryDoc() {
        var videoName = "The Lord of the Rings";
        var payment = PaymentBuilder.create()
                .withDeliveryDoc()
                .withVideo(135L, videoName)
                .build();
        when(paymentHandlerContext.payment())
                .thenReturn(payment);
        isVideoLawEnforcement1997PaymentHandler.doNext(paymentHandlerContext, paymentHandlerChain);
        verify(paymentHandlerChain).next();
        var containPrimeirosSocorros = payment.deliveryDoc()
                .items()
                .stream()
                .anyMatch(deliveryDocItem -> "Primeiros Socorros".equalsIgnoreCase(deliveryDocItem.name()));
        assertFalse(containPrimeirosSocorros);
    }
}