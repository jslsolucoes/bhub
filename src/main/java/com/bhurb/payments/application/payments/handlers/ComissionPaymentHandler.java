package com.bhurb.payments.application.payments.handlers;


import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.model.entities.payments.specs.IsBookSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.IsPhysicalSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.Spec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ComissionPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ComissionPaymentHandler.class);


    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {

        var paymentSpec = (PaymentSpec) paymentHandlerContext.get("paymentSpec");
        var isPhysical = new IsPhysicalSpec();
        var isBook = new IsBookSpec();
        var specs = Spec.or(isBook, isPhysical);
        if (specs.isSatisfiedBy(paymentSpec)) {
            LOGGER.info("comission = 5%");
        }
        paymentHandlerChain.next();
    }

}
