package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.model.entities.payments.specs.IsPhysicalSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@PaymentHandlerPriority(after = {LogPaymentHandler.class})
@Component
public class IsPhysicalPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsPhysicalPaymentHandler.class);


    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {
        var payment = paymentHandlerContext.payment();
        var product = payment.product();
        var isPhysicalSpec = new IsPhysicalSpec();
        if (isPhysicalSpec.isSatisfiedBy(product)) {
            LOGGER.debug("isPhysical = true");
        }
        paymentHandlerChain.next();
    }

}
