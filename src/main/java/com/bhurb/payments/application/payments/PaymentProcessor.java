package com.bhurb.payments.application.payments;


import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChainProcessor;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PaymentProcessor {

    private final List<PaymentHandler> paymentHandlers;

    @Autowired
    public PaymentProcessor(final List<PaymentHandler> paymentHandlers) {
        this.paymentHandlers = paymentHandlers;
    }

    public PaymentProcessorOutput process(final PaymentProcessorInput paymentProcessorInput) {
        var paymentHandlerChainProcessor = new PaymentHandlerChainProcessor();
        paymentHandlers.forEach(paymentHandlerChainProcessor::register);
        var paymentHandlerContext = new PaymentHandlerContext();
        paymentHandlerContext.put("paymentSpec", paymentProcessorInput.paymentSpec());
        paymentHandlerChainProcessor.next(paymentHandlerContext);
        return new PaymentProcessorOutput(1L);
    }

    public record PaymentProcessorInput(LocalDateTime createdAt,
                                        Long customerId,
                                        String customerEmail,
                                        BigDecimal amount,
                                        PaymentSpec paymentSpec) {

    }

    public record PaymentProcessorOutput(Long paymentId) {

    }
}
