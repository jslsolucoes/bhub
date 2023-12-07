package com.bhurb.payments.application.payments;


import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PaymentProcessor {

    private final List<Filter> filters;

    @Autowired
    public PaymentProcessor(final List<Filter> filters) {
        this.filters = filters;
    }

    public PaymentProcessorOutput process(final PaymentProcessorInput paymentProcessorInput) {
        var filterChainProcessor = new FilterChainProcessor();
        filters.forEach(filterChainProcessor::register);
        var filterContext = new FilterContext();
        filterContext.put("paymentSpec", paymentProcessorInput.paymentSpec());
        filterChainProcessor.next(filterContext);
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
