package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.model.entities.payments.specs.IsBookSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Component
@PaymentHandlerPriority(after = {DeliveryDocPaymentHandler.class})
public class RoyaltDeliveryDocPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RoyaltDeliveryDocPaymentHandler.class);

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {
        var payment = paymentHandlerContext.payment();
        var product = payment.product();
        var isBookSpec = new IsBookSpec();
        if (isBookSpec.isSatisfiedBy(product)) {
            LOGGER.debug("clone delivery doc for royalty department");
        }
        paymentHandlerChain.next();
    }
}
