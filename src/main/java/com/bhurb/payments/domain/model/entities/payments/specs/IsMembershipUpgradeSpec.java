package com.bhurb.payments.domain.model.entities.payments.specs;

import com.bhurb.payments.domain.model.entities.products.MembershipPayment.MembershipPlan;

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
