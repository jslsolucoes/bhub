package com.bhurb.payments.domain.model.entities.payments.specs;

import java.util.stream.Stream;

public class OrSpec implements Spec {

    private final Spec[] specs;

    public OrSpec(Spec... specs) {
        this.specs = specs;
    }

    @Override
    public boolean isSatisfiedBy(final PaymentSpec paymentSpec) {
        return Stream.of(specs).anyMatch(spec -> spec.isSatisfiedBy(paymentSpec));
    }
}
