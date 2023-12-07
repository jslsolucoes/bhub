package com.bhurb.payments.application.payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doNext(final FilterContext filterContext, final FilterChain filterChain) {
        LOGGER.info("LogFilter");
        filterChain.next();
    }

}
