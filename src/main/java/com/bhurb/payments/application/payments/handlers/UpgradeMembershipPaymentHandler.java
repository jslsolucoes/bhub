package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.events.EventDispatcher;
import com.bhurb.payments.domain.model.entities.specs.IsMembershipUpgradeSpec;
import org.springframework.stereotype.Component;


@PaymentHandlerPriority(after = {NewMembershipPaymentHandler.class})
@Component
public class UpgradeMembershipPaymentHandler implements PaymentHandler {

    private final EventDispatcher eventDispatcher;

    public UpgradeMembershipPaymentHandler(final EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {

        var payment = paymentHandlerContext.payment();
        var customer = payment.customer();
        var product = payment.product();

        var isMembershipUpgradeSpec = new IsMembershipUpgradeSpec();
        if (isMembershipUpgradeSpec.isSatisfiedBy(product)) {
            var customerId = customer.refId();
            var membershipPayment = product.asMembershipPayment();
            var membershipPlan = membershipPayment.membershipPlan();
            eventDispatcher.upgradeMembership(customerId, membershipPlan);
            var to = customer.email()
                    .address();
            var message = "Your plan was upgraded to %s!".formatted(membershipPlan.title());
            eventDispatcher.newMail(to, "Your plan was upgraded!", message);
        }

        paymentHandlerChain.next();
    }
}
