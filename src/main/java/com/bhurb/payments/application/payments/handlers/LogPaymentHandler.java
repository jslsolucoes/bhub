package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPaymentHandler.class);

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext, final PaymentHandlerChain paymentHandlerChain) {
        LOGGER.info("Log payment handler");
        paymentHandlerChain.next();
    }

}
