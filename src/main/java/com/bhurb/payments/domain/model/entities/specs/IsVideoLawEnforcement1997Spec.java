package com.bhurb.payments.domain.model.entities.specs;

public class IsVideoLawEnforcement1997Spec implements Spec {
    @Override
    public boolean isSatisfiedBy(final PaymentSpec paymentSpec) {
        return paymentSpec.isVideoLawEnforcement1997();
    }
}
