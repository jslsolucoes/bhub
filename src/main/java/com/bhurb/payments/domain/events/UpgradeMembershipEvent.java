package com.bhurb.payments.domain.events;

import com.bhurb.payments.domain.model.entities.products.MembershipPayment;
import org.springframework.context.ApplicationEvent;

public class UpgradeMembershipEvent extends ApplicationEvent
        implements DomainEvent<UpgradeMembershipEvent.UpgradeMembershipEventDetails> {

    public UpgradeMembershipEvent(final Long customerId,
                                  final MembershipPayment.MembershipPlan membershipPlan) {
        super(new UpgradeMembershipEventDetails(customerId, membershipPlan));
    }

    @Override
    public UpgradeMembershipEventDetails source() {
        return (UpgradeMembershipEventDetails) getSource();
    }

    public record UpgradeMembershipEventDetails(Long customerId, MembershipPayment.MembershipPlan membershipPlan) {
    }
}
