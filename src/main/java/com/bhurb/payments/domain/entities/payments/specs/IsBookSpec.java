package com.bhurb.payments.domain.entities.payments.specs;

public class IsBookSpec implements Spec {

    @Override
    public boolean isSatisfiedBy(final PaymentSpec paymentSpec) {
        return paymentSpec.isBook();
    }
}
