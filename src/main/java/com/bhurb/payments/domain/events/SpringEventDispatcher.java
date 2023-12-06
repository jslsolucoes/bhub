package com.bhurb.payments.domain.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringEventDispatcher implements EventDispatcher {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringEventDispatcher(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


}
