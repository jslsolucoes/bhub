package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.events.EventDispatcher;
import com.bhurb.payments.domain.model.entities.MembershipPayment;
import com.bhurb.payments.domain.model.entities.PaymentBuilder;
import com.bhurb.payments.junit.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class NewMembershipPaymentHandlerTest extends AbstractIntegrationTest {


    @Autowired
    private NewMembershipPaymentHandler newMembershipPaymentHandler;

    @MockBean
    private PaymentHandlerContext paymentHandlerContext;

    @MockBean
    private PaymentHandlerChain paymentHandlerChain;

    @MockBean
    private EventDispatcher eventDispatcher;


    @Test
    @DisplayName("Should create a new membership")
    void shouldCreateNewMembership() {
        var payment = PaymentBuilder.create()
                .withCustomerWithoutMembership()
                .withMembership(1L, MembershipPayment.MembershipPlan.BASIC)
                .build();
        when(paymentHandlerContext.payment())
                .thenReturn(payment);
        newMembershipPaymentHandler.doNext(paymentHandlerContext, paymentHandlerChain);
        verify(paymentHandlerChain).next();
        verify(eventDispatcher).newMembership(any(), any());
        verify(eventDispatcher).newMail(any(), eq("Welcome to the club!"), eq("You are now a member of the club!"));
    }


}