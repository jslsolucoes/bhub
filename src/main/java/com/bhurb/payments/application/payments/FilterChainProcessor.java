package com.bhurb.payments.application.payments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class FilterChainProcessor {

    private final List<Filter> filters;

    public FilterChainProcessor() {
        this.filters = new ArrayList<>();
    }

    public void register(final Filter filter) {
        this.filters.add(filter);
    }

    public void next(final FilterContext filterContext) {
        var filtersWithPriority = this.filters
                .stream().sorted(Comparator.comparing(Filter::priority))
                .toList();
        var filterChain = new DefaultFilterChain(filtersWithPriority, filterContext);
        filterChain.next();
    }

}
