package com.bhurb.payments.application.payments.handlers;


import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.repos.PaymentRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PaymentHandlerPriority(after = {ComissionPaymentHandler.class})
@Component
public class FinishPaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FinishPaymentHandler.class);
    private final PaymentRepository paymentRepository;

    @Autowired
    public FinishPaymentHandler(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {
        var payment = paymentHandlerContext.payment();
        LOGGER.info("Finishing payment ...{}", payment);
        paymentRepository.save(payment);
        paymentHandlerChain.next();
    }

}
