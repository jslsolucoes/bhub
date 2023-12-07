package com.bhurb.payments.domain.model.entities.payments.specs;

public interface PaymentSpec {
    default boolean isBook() {
        return false;
    }

    default boolean isMembershipUpgrade() {
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
