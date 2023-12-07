package com.bhurb.payments.domain.model.entities.specs;

import java.util.stream.Stream;

public class AndSpec implements Spec {

    private final Spec[] specs;

    public AndSpec(Spec... specs) {
        this.specs = specs;
    }

    @Override
    public boolean isSatisfiedBy(final PaymentSpec paymentSpec) {
        return Stream.of(specs).allMatch(spec -> spec.isSatisfiedBy(paymentSpec));
    }
}
