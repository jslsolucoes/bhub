package com.bhurb.payments.domain.model.entities.specs;

import com.bhurb.payments.domain.model.entities.BookPayment;
import com.bhurb.payments.domain.model.entities.MembershipPayment;
import com.bhurb.payments.domain.model.entities.VideoPayment;
import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class IsBookSpecTest extends AbstractTest {

    @Test
    @DisplayName("Should return true when payment is a book")
    void shouldReturnTrueWhenPaymentIsABook() {
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.DIGITAL, null);
        var isBookSpec = new IsBookSpec();
        assertTrue(isBookSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when payment is video")
    void shouldReturnFalseWhenPaymentIsVideo() {
        var paymentSpec = new VideoPayment(null, 1L, "somvideo", null);
        var isBookSpec = new IsBookSpec();
        assertFalse(isBookSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when payment is membership")
    void shouldReturnFalseWhenPaymentIsMembership() {
        var paymentSpec = new MembershipPayment(null, 1L, MembershipPayment.MembershipPlan.BASIC,
                null);
        var isBookSpec = new IsBookSpec();
        assertFalse(isBookSpec.isSatisfiedBy(paymentSpec));
    }
}