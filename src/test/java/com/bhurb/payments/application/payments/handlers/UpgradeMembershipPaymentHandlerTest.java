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

class UpgradeMembershipPaymentHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private UpgradeMembershipPaymentHandler upgradeMembershipPaymentHandler;

    @MockBean
    private PaymentHandlerContext paymentHandlerContext;

    @MockBean
    private PaymentHandlerChain paymentHandlerChain;

    @MockBean
    private EventDispatcher eventDispatcher;

    @Test
    @DisplayName("Should upgrade a current membership")
    void shouldUpgradeCurrentMembership() {
        var payment = PaymentBuilder.create()
                .withCustomerWithMembership(MembershipPayment.MembershipPlan.BASIC)
                .withMembership(1L, MembershipPayment.MembershipPlan.PREMIUM)
                .build();
        when(paymentHandlerContext.payment())
                .thenReturn(payment);
        upgradeMembershipPaymentHandler.doNext(paymentHandlerContext, paymentHandlerChain);
        verify(paymentHandlerChain).next();
        verify(eventDispatcher).upgradeMembership(
                eq(payment.customer().refId()),
                eq(MembershipPayment.MembershipPlan.PREMIUM)
        );
        verify(eventDispatcher).newMail(any(), eq("Your plan was upgraded!"), any());
    }
}