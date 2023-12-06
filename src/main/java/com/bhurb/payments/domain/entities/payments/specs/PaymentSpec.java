package com.bhurb.payments.domain.entities.payments.specs;

import com.bhurb.payments.domain.entities.products.Membership.MembershipPlan;

public interface PaymentSpec {
    default boolean isBook() {
        return false;
    }

    default boolean isMembershipUpgrade(final MembershipPlan newPlan) {
        return false;
    }

    default boolean isNewMembership() {
        return false;
    }

    default boolean isPhysical() {
        return false;
    }

    default boolean isVideoLawEnforcement1997() {
        return false;
    }
}
