package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.model.entities.specs.IsPhysicalSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Component
@PaymentHandlerPriority(after = {LogPaymentHandler.class})
public class DeliveryDocPaymentHandler implements PaymentHandler {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DeliveryDocPaymentHandler.class);

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {
        var payment = paymentHandlerContext.payment();
        var product = payment.product();
        var isPhysicalSpec = new IsPhysicalSpec();
        if (isPhysicalSpec.isSatisfiedBy(product)) {
            var deliveryDocItem = product.asDeliveryDocItem()
                    .orElseThrow(() -> new IllegalStateException("Since product is physical must act as delivery doc item"));
            payment.createNewDeliveryDoc()
                    .addItemToDeliveryDoc(deliveryDocItem);
        }
        paymentHandlerChain.next();
    }
}
