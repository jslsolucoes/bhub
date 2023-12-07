package com.bhurb.payments.application.payments;

import java.util.LinkedList;
import java.util.List;

class DefaultFilterChain implements FilterChain {

    private final LinkedList<Filter> filters;
    private final FilterContext filterContext;

    public DefaultFilterChain(List<Filter> filters, FilterContext filterContext) {
        this.filters = new LinkedList<>(filters);
        this.filterContext = filterContext;
    }

    @Override
    public void next() {

        if (this.filters.isEmpty()) {
            return;
        }
        var head = this.filters.removeFirst();
        head.doNext(this.filterContext, this);
    }
}


