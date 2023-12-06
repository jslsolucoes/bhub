package com.bhurb.payments.domain.entities.payments.specs;

public class IsNewMembershipSpec implements Spec {

    @Override
    public boolean isSatisfiedBy(final PaymentSpec paymentSpec) {
        return paymentSpec.isNewMembership();
    }
}
