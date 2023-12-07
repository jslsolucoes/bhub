package com.bhurb.payments.application.payments;

import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class IsPhysicalFilter implements Filter {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsPhysicalFilter.class);


    @Override
    public void doNext(FilterContext filterContext, FilterChain filterChain) {
        var paymentSpec = (PaymentSpec) filterContext.get("paymentSpec");
        if (paymentSpec.isPhysical()) {
            LOGGER.info("isPhysical = true");
        }
        filterChain.next();
    }

    @Override
    public int priority() {
        return 0;
    }
}
