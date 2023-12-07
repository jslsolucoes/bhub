package com.bhurb.payments.application.payments;

public interface Filter {
    void doNext(final FilterContext filterContext, final FilterChain filterChain);

    int priority();
}
