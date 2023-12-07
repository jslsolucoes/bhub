package com.bhurb.payments.application.payments;

import java.util.HashMap;
import java.util.Map;

class FilterChainProcessor {

    private final Map<Class<? extends Filter>, Filter> filters;

    public FilterChainProcessor() {
        this.filters = new HashMap<>();
    }

    public void register(final Filter filter) {
        this.filters.put(filter.getClass(), filter);
    }

    public void next(final FilterContext filterContext) {
        var filterClasses = this.filters
                .keySet()
                .toArray(Class<?>[]::new);
        var topologicalSortFilters = new TopologicalSortFilters();
        topologicalSortFilters.register(filterClasses);
        var filtersWithPriority = topologicalSortFilters.all();
        var newFilters = filtersWithPriority
                .stream()
                .map(this.filters::get)
                .toList();
        var defaultFilterChain = new DefaultFilterChain(newFilters, filterContext);
        defaultFilterChain.next();
    }

}
