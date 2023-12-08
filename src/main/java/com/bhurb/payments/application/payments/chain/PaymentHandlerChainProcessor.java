package com.bhurb.payments.application.payments.chain;


import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class PaymentHandlerChainProcessor {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PaymentHandlerChainProcessor.class);
    private final Map<Class<? extends PaymentHandler>, PaymentHandler> paymentHandlers;

    public PaymentHandlerChainProcessor() {
        this.paymentHandlers = new HashMap<>();
    }

    public void register(final PaymentHandler paymentHandler) {
        this.paymentHandlers.put(paymentHandler.getClass(), paymentHandler);
    }

    public void next(final PaymentHandlerContext paymentHandlerContext) {
        /*
            * 1. Get all the payment handlers
            * 2. Sort them by priority using topological sort
            * 3. Create a new chain with the sorted payment handlers
            * 4. Run the chain
         */
        var paymentHandlerClasses = this.paymentHandlers
                .keySet()
                .toArray(Class<?>[]::new);
        var topologicalSort = new TopologicalSort();
        topologicalSort.register(paymentHandlerClasses);
        var paymentHandlersWithPriority = topologicalSort.all();

        LOGGER.debug("Handlers will run in this order {}", paymentHandlersWithPriority);

        var newPaymentHandlers = paymentHandlersWithPriority
                .stream()
                .map(this.paymentHandlers::get)
                .toList();
        var defaultPaymentHandlerChain = new DefaultPaymentHandlerChain(newPaymentHandlers, paymentHandlerContext);
        defaultPaymentHandlerChain.next();
    }

}
