package com.bhurb.payments.domain.model.entities.products;

import com.bhurb.payments.domain.model.entities.payments.Payment;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class VideoPayment extends ProductPayment {


    @NotNull
    protected final String name;

    @Deprecated
    public VideoPayment() {
        this(null, null, null);
    }

    public VideoPayment(final Long id,
                        final String name,
                        final Payment payment) {
        super(id, payment);
        this.name = name;
    }

    @Override
    public VideoPayment withPayment(final Payment payment) {
        return new VideoPayment(id, name, payment);
    }

    @Override
    public boolean isVideoLawEnforcement1997() {
        return "Aprendendo a Esquiar".equalsIgnoreCase(name);
    }

    @Override
    public String toString() {
        return "VideoPayment{" +
                "name='" + name + '\'' +
                '}';
    }
}
