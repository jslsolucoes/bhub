package com.bhurb.payments.domain.entities.products;


import com.bhurb.payments.domain.entities.payments.specs.PaymentSpec;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class Membership implements PaymentSpec {

    @Id
    @NotNull
    @GeneratedValue
    private final Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private final Membership.MembershipPlan type;

    @Override
    public boolean isNewMembership() {
        return id == null;
    }

    @Override
    public boolean isMembershipUpgrade(final MembershipPlan newPlan) {
        return id != null && type.level() < newPlan.level();
    }

    @Deprecated
    public Membership() {
        this(null, null);
    }

    public Membership(final Long id,
                      final MembershipPlan type) {
        this.id = id;
        this.type = type;
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
