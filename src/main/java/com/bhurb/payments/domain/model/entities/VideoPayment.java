package com.bhurb.payments.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@Entity
public class VideoPayment extends ProductPayment {

    @NotNull
    protected final String name;
    @NotNull
    private final Long refId;

    private static final List<String> VIDEO_LAW_ENFORCEMENT_1997_NAME = List.of("Aprendendo a Esquiar");

    @Deprecated
    public VideoPayment() {
        this(null, null, null, null);
    }

    public VideoPayment(final Long id,
                        final Long refId,
                        final String name,
                        final Payment payment) {
        super(id, payment);
        this.name = name;
        this.refId = refId;
    }

    @Override
    public VideoPayment withPayment(final Payment payment) {
        return new VideoPayment(id, refId, name, payment);
    }

    @Override
    public Optional<DeliveryDocItem> asDeliveryDocItem() {
        return Optional.of(
                new DeliveryDocItem(null, refId, name, false, null)
        );
    }

    @Override
    public boolean isVideoLawEnforcement1997() {
        return VIDEO_LAW_ENFORCEMENT_1997_NAME.stream()
                .anyMatch(n -> n.equalsIgnoreCase(name));
    }

    @Override
    public String toString() {
        return "VideoPayment{" +
                "name='" + name + '\'' +
                '}';
    }
}
