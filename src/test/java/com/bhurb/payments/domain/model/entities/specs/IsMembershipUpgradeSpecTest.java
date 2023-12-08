package com.bhurb.payments.domain.model.entities.specs;

import com.bhurb.payments.domain.model.entities.MembershipPayment;
import com.bhurb.payments.domain.model.entities.PaymentBuilder;
import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IsMembershipUpgradeSpecTest extends AbstractTest {

    @Test
    @DisplayName("Should return true when payment is a membership upgrade")
    void shouldReturnTrueWhenPaymentIsAMembershipUpgrade() {
        var payment = PaymentBuilder
                .create()
                .withCustomerWithMembership(MembershipPayment.MembershipPlan.BASIC)
                .build();
        var paymentSpec = new MembershipPayment(null, 1L, MembershipPayment.MembershipPlan.PREMIUM,
                payment);
        var isMembershipUpgradeSpec = new IsMembershipUpgradeSpec();
        assertTrue(isMembershipUpgradeSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when payment is a membership with same plan")
    void shouldReturnTrueWhenPaymentIsAMembershipWithSamePlan() {
        var payment = PaymentBuilder
                .create()
                .withCustomerWithMembership(MembershipPayment.MembershipPlan.BASIC)
                .build();
        var paymentSpec = new MembershipPayment(null, 1L, MembershipPayment.MembershipPlan.BASIC,
                payment);
        var isMembershipUpgradeSpec = new IsMembershipUpgradeSpec();
        assertFalse(isMembershipUpgradeSpec.isSatisfiedBy(paymentSpec));
    }

}