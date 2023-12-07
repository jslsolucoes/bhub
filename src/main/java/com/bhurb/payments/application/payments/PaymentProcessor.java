package com.bhurb.payments.application.payments;


import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PaymentProcessor {


    @Autowired
    public PaymentProcessor(List<PaymentSpec> specs) {

    }

    public PaymentProcessorOutput process(final PaymentProcessorInput paymentProcessorInput) {


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
