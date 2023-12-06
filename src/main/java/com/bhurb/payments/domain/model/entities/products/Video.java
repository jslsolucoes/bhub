package com.bhurb.payments.domain.model.entities.products;

import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Video implements PaymentSpec {

    @Id
    @NotNull
    @GeneratedValue
    protected final Long id;

    @NotNull
    protected final String name;

    @Deprecated
    public Video() {
        this(null, null);
    }


    public Video(final Long id,
                 final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean isVideoLawEnforcement1997() {
        return "Aprendendo a Esquiar".equalsIgnoreCase(name);
    }
}
