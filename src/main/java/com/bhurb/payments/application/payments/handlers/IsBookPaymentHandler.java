package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.model.entities.payments.specs.IsBookSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@PaymentHandlerPriority(after = {LogPaymentHandler.class})
public class IsBookPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsBookPaymentHandler.class);

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext, final PaymentHandlerChain paymentHandlerChain) {
        var paymentSpec = (PaymentSpec) paymentHandlerContext.get("paymentSpec");
        var isBookSpec = new IsBookSpec();
        if (isBookSpec.isSatisfiedBy(paymentSpec)) {
            LOGGER.info("isBook = true");
        }
        paymentHandlerChain.next();
    }

}
