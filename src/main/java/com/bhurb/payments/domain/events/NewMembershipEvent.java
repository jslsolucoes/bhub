package com.bhurb.payments.domain.events;

import com.bhurb.payments.domain.model.entities.MembershipPayment;
import org.springframework.context.ApplicationEvent;

public class NewMembershipEvent extends ApplicationEvent
        implements DomainEvent<NewMembershipEvent.NewMembershipEventDetails> {

    public NewMembershipEvent(final Long customerId,
                              final MembershipPayment.MembershipPlan membershipPlan) {
        super(new NewMembershipEventDetails(customerId, membershipPlan));
    }

    @Override
    public NewMembershipEventDetails source() {
        return (NewMembershipEventDetails) getSource();
    }

    public record NewMembershipEventDetails(Long customerId, MembershipPayment.MembershipPlan membershipPlan) {
    }
}
