package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.Filter;
import com.bhurb.payments.application.payments.chain.FilterChain;
import com.bhurb.payments.application.payments.chain.FilterContext;
import com.bhurb.payments.application.payments.chain.FilterPriority;
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
