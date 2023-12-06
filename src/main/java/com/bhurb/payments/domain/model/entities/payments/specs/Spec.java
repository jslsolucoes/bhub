package com.bhurb.payments.domain.model.entities.payments.specs;

public interface Spec {

    boolean isSatisfiedBy(final PaymentSpec paymentSpec);

    static Spec and(Spec... specs) {
        return new AndSpec(specs);
    }

    static Spec or(Spec... specs) {
        return new OrSpec(specs);
    }

}

