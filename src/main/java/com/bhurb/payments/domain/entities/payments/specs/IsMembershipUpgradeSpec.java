package com.bhurb.payments.domain.entities.payments.specs;

import com.bhurb.payments.domain.entities.products.Membership.MembershipPlan;

public class IsMembershipUpgradeSpec implements Spec {
    private final MembershipPlan newPlan;

    public IsMembershipUpgradeSpec(final MembershipPlan newPlan) {
        this.newPlan = newPlan;
    }

    @Override
    public boolean isSatisfiedBy(final PaymentSpec paymentSpec) {
        return paymentSpec.isMembershipUpgrade(this.newPlan);
    }
}
