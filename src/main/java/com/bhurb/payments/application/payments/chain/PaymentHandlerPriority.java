package com.bhurb.payments.application.payments.chain;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PaymentHandlerPriority {
    Class<?>[] before() default {};
    Class<?>[] after() default {};
}
