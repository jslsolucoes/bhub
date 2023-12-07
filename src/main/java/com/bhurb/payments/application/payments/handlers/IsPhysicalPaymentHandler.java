package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class IsPhysicalPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsPhysicalPaymentHandler.class);


    @Override
    public void doNext(PaymentHandlerContext paymentHandlerContext, PaymentHandlerChain paymentHandlerChain) {
        var paymentSpec = (PaymentSpec) paymentHandlerContext.get("paymentSpec");
        if (paymentSpec.isPhysical()) {
            LOGGER.info("isPhysical = true");
        }
        paymentHandlerChain.next();
    }

}
