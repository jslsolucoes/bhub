package com.bhurb.payments.infra.events;

import com.bhurb.payments.domain.events.*;
import com.bhurb.payments.domain.model.entities.MembershipPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringEventDispatcher implements EventDispatcher {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringEventDispatcher(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void newMail(final String to,
                        final String subject,
                        final String body) {
        this.applicationContext.publishEvent(new NewMailEvent(to, subject, body));
    }

    @Override
    public void newMembership(final Long customerId,
                              final MembershipPayment.MembershipPlan membershipPlan) {
        this.applicationContext.publishEvent(new NewMembershipEvent(customerId, membershipPlan));
    }

    @Override
    public void upgradeMembership(final Long customerId,
                                  final MembershipPayment.MembershipPlan membershipPlan) {
        this.applicationContext.publishEvent(new UpgradeMembershipEvent(customerId, membershipPlan));
    }

    @Override
    public void newBookRoyaltDeliveryDoc(final Long bookId,
                                         final String author) {
        this.applicationContext.publishEvent(new NewBookRoyaltDeliveryDocEvent(bookId, author));
    }

}
