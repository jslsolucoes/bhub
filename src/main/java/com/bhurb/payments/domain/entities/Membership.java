package com.bhurb.payments.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;


@Entity
public class Membership extends Product {

    @Enumerated(value = jakarta.persistence.EnumType.STRING)
    private final MembershipType type;

    @Deprecated
    public Membership() {
        this(null, null, null, null);
    }

    public Membership(final Long id,
                      final String name,
                      final MembershipType type,
                      final Payment payment) {
        super(id, name, payment);
        this.type = type;
    }

    @Override
    public Membership withPayment(final Payment payment) {
        return new Membership(id, name, type, payment);
    }

    public boolean isUpgrade(final MembershipType membershipType) {
        return type.priority() < membershipType.priority();
    }

    enum MembershipType {
        BASIC(1),
        PREMIUM(2);

        private final int priority;

        MembershipType(final int priority) {
            this.priority = priority;
        }

        public boolean isBasic() {
            return this == MembershipType.BASIC;
        }

        public boolean isPremium() {
            return this == MembershipType.PREMIUM;
        }

        public int priority() {
            return priority;
        }

    }

}
