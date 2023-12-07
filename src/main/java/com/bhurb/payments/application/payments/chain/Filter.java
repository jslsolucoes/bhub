package com.bhurb.payments.application.payments.chain;

public interface Filter {
    void doNext(final FilterContext filterContext, final FilterChain filterChain);

}
