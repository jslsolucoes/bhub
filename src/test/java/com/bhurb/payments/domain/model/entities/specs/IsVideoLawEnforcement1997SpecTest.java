package com.bhurb.payments.domain.model.entities.specs;

import com.bhurb.payments.domain.model.entities.MembershipPayment;
import com.bhurb.payments.domain.model.entities.VideoPayment;
import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IsVideoLawEnforcement1997SpecTest extends AbstractTest {
    @Test
    @DisplayName("Should return true when payment is video Aprendendo a Esquiar")
    void shouldReturnTrueWhenPaymentIsVideoLawEnforcement1997() {
        var paymentSpec = new VideoPayment(null, 1L, "Aprendendo a Esquiar", null);
        var isVideoLawEnforcement1997Spec = new IsVideoLawEnforcement1997Spec();
        assertTrue(isVideoLawEnforcement1997Spec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when payment is not video Aprendendo a Esquiar")
    void shouldReturnFalseWhenPaymentIsNotVideoLawEnforcement1997() {
        var paymentSpec = new VideoPayment(null, 2L, "Magico de Oz", null);
        var isVideoLawEnforcement1997Spec = new IsVideoLawEnforcement1997Spec();
        assertFalse(isVideoLawEnforcement1997Spec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when product payment is video")
    void shouldReturnFalseWhenPaymentIsVideo() {
        var paymentSpec = new VideoPayment(null, 1L, "somvideo", null);
        var isVideoLawEnforcement1997Spec = new IsVideoLawEnforcement1997Spec();
        assertFalse(isVideoLawEnforcement1997Spec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when product payment is membership")
    void shouldReturnFalseWhenPaymentIsMembership() {
        var paymentSpec = new MembershipPayment(null, 1L, MembershipPayment.MembershipPlan.BASIC,
                null);
        var isVideoLawEnforcement1997Spec = new IsVideoLawEnforcement1997Spec();
        assertFalse(isVideoLawEnforcement1997Spec.isSatisfiedBy(paymentSpec));
    }
}