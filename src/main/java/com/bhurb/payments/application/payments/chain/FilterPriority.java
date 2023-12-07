package com.bhurb.payments.application.payments.chain;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FilterPriority {
    Class<?>[] before() default {};
    Class<?>[] after() default {};
}
