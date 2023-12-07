package com.bhurb.payments.application.payments;


import com.bhurb.payments.domain.model.entities.payments.specs.IsBookSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.IsPhysicalSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.PaymentSpec;
import com.bhurb.payments.domain.model.entities.payments.specs.Spec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ComissionFilter implements Filter {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ComissionFilter.class);


    @Override
    public void doNext(final FilterContext filterContext,
                       final FilterChain filterChain) {

        var paymentSpec = (PaymentSpec) filterContext.get("paymentSpec");
        var isPhysical = new IsPhysicalSpec();
        var isBook = new IsBookSpec();
        var specs = Spec.or(isBook, isPhysical);
        if (specs.isSatisfiedBy(paymentSpec)) {
            LOGGER.info("comission = 5%");
        }
        filterChain.next();
    }

    @Override
    public int priority() {
        return 1;
    }
}