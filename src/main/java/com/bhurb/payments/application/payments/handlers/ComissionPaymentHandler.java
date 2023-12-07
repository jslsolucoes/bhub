package com.bhurb.payments.application.payments.handlers;


import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.model.entities.Comission;
import com.bhurb.payments.domain.model.entities.specs.IsBookSpec;
import com.bhurb.payments.domain.model.entities.specs.IsPhysicalSpec;
import com.bhurb.payments.domain.model.entities.specs.Spec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@PaymentHandlerPriority(after = {IsVideoLawEnforcement1997PaymentHandler.class})
@Component
public class ComissionPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ComissionPaymentHandler.class);

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {

        var payment = paymentHandlerContext.payment();
        LOGGER.debug("Generating comission payment {}", payment);

        var product = payment.product();
        var isPhysical = new IsPhysicalSpec();
        var isBook = new IsBookSpec();
        var specs = Spec.or(isBook, isPhysical);
        if (specs.isSatisfiedBy(product)) {
            var seller = payment.seller();
            var comission = new Comission(
                    null,
                    seller,
                    payment
            );
            payment.addComission(comission);
        }
        paymentHandlerChain.next();
    }

}
