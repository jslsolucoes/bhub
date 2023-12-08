package com.bhurb.payments.domain.events;

import com.bhurb.payments.domain.model.entities.DeliveryDoc;
import com.bhurb.payments.domain.model.entities.MembershipPayment;

public interface EventDispatcher {
    void newMail(final String to,
                 final String subject,
                 final String body);

    void newMembership(final Long customerId,
                       final MembershipPayment.MembershipPlan membershipPlan);

    void upgradeMembership(final Long customerId,
                           final MembershipPayment.MembershipPlan membershipPlan);

    void newBookRoyaltDeliveryDoc(final DeliveryDoc deliveryDoc);
}
