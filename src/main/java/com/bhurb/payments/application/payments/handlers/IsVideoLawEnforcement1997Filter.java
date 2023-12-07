package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.Filter;
import com.bhurb.payments.application.payments.chain.FilterChain;
import com.bhurb.payments.application.payments.chain.FilterContext;
import com.bhurb.payments.domain.model.entities.payments.specs.IsVideoLawEnforcement1997Spec;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class IsVideoLawEnforcement1997Filter implements Filter {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsVideoLawEnforcement1997Filter.class);

    @Override
    public void doNext(final FilterContext filterContext,
                       final FilterChain filterChain) {
        var paymentSpec = (PaymentSpec) filterContext.get("paymentSpec");
        var isVideoLawEnforcement1997Spec = new IsVideoLawEnforcement1997Spec();
        if (isVideoLawEnforcement1997Spec.isSatisfiedBy(paymentSpec)) {
            LOGGER.info("isVideoLawEnforcement1997 = true add Primeiros Socorros");
        }
    }

}
