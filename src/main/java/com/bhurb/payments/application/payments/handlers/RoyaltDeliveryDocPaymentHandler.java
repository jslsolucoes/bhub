package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.events.EventDispatcher;
import com.bhurb.payments.domain.model.entities.specs.IsBookSpec;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@PaymentHandlerPriority(after = {DeliveryDocPaymentHandler.class})
public class RoyaltDeliveryDocPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RoyaltDeliveryDocPaymentHandler.class);

    private final EventDispatcher eventDispatcher;

    @Autowired
    public RoyaltDeliveryDocPaymentHandler(final EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }
    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {
        var payment = paymentHandlerContext.payment();
        var product = payment.product();
        var isBookSpec = new IsBookSpec();
        if (isBookSpec.isSatisfiedBy(product)) {
            LOGGER.info("Will generate a Royalt Delivery Doc");
            var deliveryDoc = payment.deliveryDoc()
                    .clone();
            eventDispatcher.newBookRoyaltDeliveryDoc(deliveryDoc);
        }
        paymentHandlerChain.next();
    }
}
