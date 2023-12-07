package com.bhurb.payments.domain.model.entities.specs;

public class IsPhysicalSpec implements Spec {

    @Override
    public boolean isSatisfiedBy(final PaymentSpec paymentSpec) {
        return paymentSpec.isPhysical();
    }
}
