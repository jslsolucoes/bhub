package com.bhurb.payments.domain.model.entities.products;


import com.bhurb.payments.domain.model.entities.payments.Payment;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProductPayment implements PaymentSpec {

    @Id
    @GeneratedValue
    @NotNull
    protected final Long id;

    @OneToOne
    protected final Payment payment;

    @Deprecated
    public ProductPayment() {
        this(null, null);
    }

    public ProductPayment(final Long id,
                          final Payment payment) {
        this.id = id;
        this.payment = payment;
    }

    public abstract ProductPayment withPayment(final Payment payment);


    public MembershipPayment asMembershipPayment() {
        if (this instanceof MembershipPayment membershipPayment) {
            return membershipPayment;
        }
        throw new IllegalStateException("This product payment is not a membership payment");
    }
}
