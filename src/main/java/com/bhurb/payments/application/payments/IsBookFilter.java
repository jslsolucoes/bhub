package com.bhurb.payments.application.payments;

import com.bhurb.payments.domain.model.entities.payments.specs.IsBookSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@FilterPriority(after = {LogFilter.class})
public class IsBookFilter implements Filter {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsBookFilter.class);

    @Override
    public void doNext(final FilterContext filterContext, final FilterChain filterChain) {
        var paymentSpec = (PaymentSpec) filterContext.get("paymentSpec");
        var isBookSpec = new IsBookSpec();
        if (isBookSpec.isSatisfiedBy(paymentSpec)) {
            LOGGER.info("isBook = true");
        }
        filterChain.next();
    }

}
