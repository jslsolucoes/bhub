package com.bhurb.payments.domain.model.entities.specs;

import com.bhurb.payments.domain.model.entities.BookPayment;
import com.bhurb.payments.domain.model.entities.MembershipPayment;
import com.bhurb.payments.domain.model.entities.VideoPayment;
import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IsPhysicalSpecTest extends AbstractTest {

    @Test
    @DisplayName("Should return true when product payment is physical and digital")
    void shouldReturnTrueWhenPaymentIsPhysicalAndDigital() {
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.BOTH, null);
        var isPhysicalSpec = new IsPhysicalSpec();
        assertTrue(isPhysicalSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return true when product payment is physical")
    void shouldReturnTrueWhenPaymentIsPhysical() {
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.PHYSICAL, null);
        var isPhysicalSpec = new IsPhysicalSpec();
        assertTrue(isPhysicalSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when product payment is digital only")
    void shouldReturnFalseWhenPaymentProductIsDigital() {
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.DIGITAL, null);
        var isPhysicalSpec = new IsPhysicalSpec();
        assertFalse(isPhysicalSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when product payment is video")
    void shouldReturnFalseWhenPaymentIsVideo() {
        var paymentSpec = new VideoPayment(null, 1L, "somvideo", null);
        var isPhysicalSpec = new IsPhysicalSpec();
        assertFalse(isPhysicalSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when product payment is membership")
    void shouldReturnFalseWhenPaymentIsMembership() {
        var paymentSpec = new MembershipPayment(null, 1L, MembershipPayment.MembershipPlan.BASIC,
                null);
        var isPhysicalSpec = new IsPhysicalSpec();
        assertFalse(isPhysicalSpec.isSatisfiedBy(paymentSpec));
    }

}