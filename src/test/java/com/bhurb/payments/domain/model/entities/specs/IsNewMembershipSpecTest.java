package com.bhurb.payments.domain.model.entities.specs;

import com.bhurb.payments.domain.model.entities.BookPayment;
import com.bhurb.payments.domain.model.entities.MembershipPayment;
import com.bhurb.payments.domain.model.entities.PaymentBuilder;
import com.bhurb.payments.domain.model.entities.VideoPayment;
import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IsNewMembershipSpecTest extends AbstractTest {

    @Test
    @DisplayName("Should return true when payment is a new membership")
    void shouldReturnTrueWhenPaymentIsANewMembership() {
        var payment = PaymentBuilder
                .create()
                .withCustomerWithoutMembership()
                .build();
        var paymentSpec = new MembershipPayment(null, 1L, MembershipPayment.MembershipPlan.BASIC,
                payment);
        var isNewMembershipSpec = new IsNewMembershipSpec();
        assertTrue(isNewMembershipSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when payment is not a new membership")
    void shouldReturnFalseWhenPaymentIsNotANewMembership() {
        var payment = PaymentBuilder
                .create()
                .withCustomerWithMembership(MembershipPayment.MembershipPlan.BASIC)
                .build();
        var paymentSpec = new MembershipPayment(null, 1L, MembershipPayment.MembershipPlan.BASIC,
                payment);
        var isNewMembershipSpec = new IsNewMembershipSpec();
        assertFalse(isNewMembershipSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when product payment is book")
    void shouldReturnFalseWhenPaymentProductIsBook() {
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.DIGITAL, null);
        var isNewMembershipSpec = new IsNewMembershipSpec();
        assertFalse(isNewMembershipSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when product payment is video")
    void shouldReturnFalseWhenPaymentIsVideo() {
        var paymentSpec = new VideoPayment(null, 1L, "somevideo", null);
        var isNewMembershipSpec = new IsNewMembershipSpec();
        assertFalse(isNewMembershipSpec.isSatisfiedBy(paymentSpec));
    }

}