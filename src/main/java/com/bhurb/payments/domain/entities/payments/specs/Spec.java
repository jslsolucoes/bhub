package com.bhurb.payments.domain.entities.payments.specs;

public interface Spec {

    boolean isSatisfiedBy(final PaymentSpec paymentSpec);

    default Spec and(Spec... specs) {
        return new AndSpec(specs);
    }

    default Spec or(Spec... specs) {
        return new OrSpec(specs);
    }

}

