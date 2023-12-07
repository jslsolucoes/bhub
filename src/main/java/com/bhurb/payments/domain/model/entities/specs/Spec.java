package com.bhurb.payments.domain.model.entities.specs;

public interface Spec {

    static Spec and(Spec... specs) {
        return new AndSpec(specs);
    }

    static Spec or(Spec... specs) {
        return new OrSpec(specs);
    }

    boolean isSatisfiedBy(final PaymentSpec paymentSpec);

}

