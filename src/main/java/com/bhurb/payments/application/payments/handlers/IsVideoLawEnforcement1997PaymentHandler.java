package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.model.entities.payments.specs.IsVideoLawEnforcement1997Spec;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class IsVideoLawEnforcement1997PaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsVideoLawEnforcement1997PaymentHandler.class);

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {
        var paymentSpec = (PaymentSpec) paymentHandlerContext.get("paymentSpec");
        var isVideoLawEnforcement1997Spec = new IsVideoLawEnforcement1997Spec();
        if (isVideoLawEnforcement1997Spec.isSatisfiedBy(paymentSpec)) {
            LOGGER.info("isVideoLawEnforcement1997 = true add Primeiros Socorros");
        }
    }

}
