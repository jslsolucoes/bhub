package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.events.EventDispatcher;
import com.bhurb.payments.domain.model.entities.payments.specs.IsNewMembershipSpec;
import org.springframework.stereotype.Component;

@Component
public class NewMembershipPaymentHandler implements PaymentHandler {

    private final EventDispatcher eventDispatcher;

    public NewMembershipPaymentHandler(final EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {

        var payment = paymentHandlerContext.payment();
        var customer = payment.customer();
        var product = payment.product();

        var isNewMembershipSpec = new IsNewMembershipSpec();
        if (isNewMembershipSpec.isSatisfiedBy(product)) {
            var customerId = customer.id();
            var membershipPayment = product.asMembershipPayment();
            var membershipPlan = membershipPayment.membershipPlan();
            eventDispatcher.newMembership(customerId, membershipPlan);
            var to = customer.email()
                    .address();
            eventDispatcher.newMail(to, "Welcome to the club!", "You are now a member of the club!");
        }

        paymentHandlerChain.next();
    }
}
