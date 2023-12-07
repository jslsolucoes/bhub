package com.bhurb.payments.application.payments;


import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChainProcessor;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.model.entities.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PaymentProcessor {

    private final List<PaymentHandler> paymentHandlers;

    @Autowired
    public PaymentProcessor(final List<PaymentHandler> paymentHandlers) {
        this.paymentHandlers = paymentHandlers;
    }

    @Transactional
    public PaymentProcessorOutput process(final PaymentProcessorInput paymentProcessorInput) {
        var payment = paymentProcessorInput.payment();
        var paymentHandlerChainProcessor = new PaymentHandlerChainProcessor();
        paymentHandlers.forEach(paymentHandlerChainProcessor::register);
        var paymentHandlerContext = new PaymentHandlerContext(payment);
        paymentHandlerChainProcessor.next(paymentHandlerContext);
        return new PaymentProcessorOutput(payment.id());
    }

    public record PaymentProcessorInput(Payment payment) {

    }

    public record PaymentProcessorOutput(Long paymentId) {

    }
}
