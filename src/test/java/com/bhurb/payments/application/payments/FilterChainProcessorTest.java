package com.bhurb.payments.application.payments;

import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FilterChainProcessorTest extends AbstractTest {

    @Test
    @DisplayName("Chain should respect filter priority")
    void chainShouldRespectFilterPriority() {

        var called = new ArrayList<Filter>();

        PriorityFilter.PriorityFilterListener priorityFilterListener = (filter, filterContext) -> {
            called.add(filter);
        };
        var filterWithPriority0 = new PriorityFilter(0, true, priorityFilterListener);
        var filterWithPriority1 = new PriorityFilter(1, true, priorityFilterListener);

        var filterChainProcessor = new FilterChainProcessor();
        filterChainProcessor.register(filterWithPriority0);
        filterChainProcessor.register(filterWithPriority1);

        var filterContext = new FilterContext();
        filterChainProcessor.next(filterContext);

        var expected = List.of(filterWithPriority0, filterWithPriority1);
        assertEquals(expected, called);
    }

    @Test
    @DisplayName("Chain should interrupt propagation after first filter")
    void chainShouldInterruptPropagationAfterFirstFilter() {

        var called = new ArrayList<Filter>();

        PriorityFilter.PriorityFilterListener priorityFilterListener = (filter, filterContext) -> {
            called.add(filter);
        };
        var filterWithPriority0 = new PriorityFilter(0, false, priorityFilterListener);
        var filterWithPriority1 = new PriorityFilter(1, true, priorityFilterListener);

        var filterChainProcessor = new FilterChainProcessor();
        filterChainProcessor.register(filterWithPriority0);
        filterChainProcessor.register(filterWithPriority1);

        var filterContext = new FilterContext();
        filterChainProcessor.next(filterContext);

        var expected = List.of(filterWithPriority0);
        assertEquals(expected, called);
    }

    @Test
    @DisplayName("Filter should pass new param context to next filter")
    void filterShouldPassNewParamContextToNextFilter() {

        var key = "somekey";
        var value = "somevalue";

        var filterWithPriority0 = new PriorityFilter(0, true, (filter, filterContext) -> {
            filterContext.put(key, value);
        });
        var filterWithPriority1 = new PriorityFilter(1, true, (filter, filterContext) -> {
            assertTrue(filterContext.containsKey(key));
            assertEquals(value, filterContext.get(key));
        });

        var filterChainProcessor = new FilterChainProcessor();
        filterChainProcessor.register(filterWithPriority0);
        filterChainProcessor.register(filterWithPriority1);

        var filterContext = new FilterContext();
        filterChainProcessor.next(filterContext);

    }

    static class PriorityFilter implements Filter {

        interface PriorityFilterListener {
            void onNext(final Filter filter, final FilterContext filterContext);
        }

        private final boolean callForNext;
        private final PriorityFilterListener priorityFilterListener;
        private final int priority;

        public PriorityFilter(final int priority,
                              final boolean callForNext,
                              final PriorityFilterListener priorityFilterListener) {
            this.priority = priority;
            this.callForNext = callForNext;
            this.priorityFilterListener = priorityFilterListener;
        }

        @Override
        public void doNext(final FilterContext filterContext, final FilterChain filterChain) {
            priorityFilterListener.onNext(this, filterContext);
            if (callForNext) {
                filterChain.next();
            }
        }

        @Override
        public int priority() {
            return priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PriorityFilter that = (PriorityFilter) o;

            return priority == that.priority;
        }

        @Override
        public int hashCode() {
            return priority;
        }
    }
}