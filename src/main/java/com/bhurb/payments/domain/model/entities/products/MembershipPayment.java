package com.bhurb.payments.domain.model.entities.products;


import com.bhurb.payments.domain.model.entities.payments.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;


@Entity
public class MembershipPayment extends ProductPayment {


    @NotNull
    @Enumerated(EnumType.STRING)
    private final MembershipPayment.MembershipPlan type;

    @Deprecated
    public MembershipPayment() {
        this(null, null, null);
    }

    public MembershipPayment(final Long id,
                             final MembershipPlan type,
                             final Payment payment) {
        super(id, payment);
        this.type = type;
    }

    @Override
    public boolean isNewMembership() {
        return id == null;
    }

    @Override
    public boolean isMembershipUpgrade(final MembershipPlan newPlan) {
        return id != null && type.level() < newPlan.level();
    }

    @Override
    public MembershipPayment withPayment(Payment payment) {
        return new MembershipPayment(id, type, payment);
    }

    @Override
    public String toString() {
        return "MembershipPayment{" +
                "type=" + type +
                '}';
    }

    public enum MembershipPlan {
        BASIC("Basic", 0),
        PREMIUM("Premium", 1);

        private final String title;
        private final int level;

        MembershipPlan(final String planName, final int level) {
            this.title = planName;
            this.level = level;
        }

        public String title() {
            return title;
        }

        public int level() {
            return level;
        }
    }
}
