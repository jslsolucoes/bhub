package com.bhurb.payments.domain.model.entities.products;


import com.bhurb.payments.domain.model.entities.payments.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;


@Entity
public class MembershipPayment extends ProductPayment {


    @NotNull
    private final Long refId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private final MembershipPayment.MembershipPlan type;

    @Deprecated
    public MembershipPayment() {
        this(null, null, null, null);
    }

    public MembershipPayment(final Long id,
                             final Long refId,
                             final MembershipPlan type,
                             final Payment payment) {
        super(id, payment);
        this.type = type;
        this.refId = refId;
    }

    @Override
    public boolean isNewMembership() {
        return payment.customer()
                .currentMembershipPlan()
                .isEmpty();
    }

    @Override
    public boolean isMembershipUpgrade() {
        return payment.customer()
                .currentMembershipPlan()
                .filter(currentMembershipPlan -> currentMembershipPlan.level() < type.level())
                .isPresent();
    }

    @Override
    public MembershipPayment withPayment(Payment payment) {
        return new MembershipPayment(id, refId, type, payment);
    }

    @Override
    public String toString() {
        return "MembershipPayment{" +
                "type=" + type +
                '}';
    }

    public MembershipPlan membershipPlan() {
        return type;
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
