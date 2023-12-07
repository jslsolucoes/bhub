package com.bhurb.payments.application.payments;

import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class FilterChainProcessorTest extends AbstractTest {


    @Test
    @DisplayName("Chain should call filter in any order when all priorities is not defined")
    void chainShouldCallFilterInAnyOrderIrPriorityNotDefined() {

        var called = new ArrayList<Filter>();

        FilterWithPriorityListener priorityFilterListener = (filter, filterContext) -> {
            called.add(filter);
        };
        var filterWithNoPriority0 = new FilterWithNoPriority0(priorityFilterListener);
        var filterWithNoPriority1 = new FilterWithNoPriority1(priorityFilterListener);

        var filterChainProcessor = new FilterChainProcessor();
        filterChainProcessor.register(filterWithNoPriority0);
        filterChainProcessor.register(filterWithNoPriority1);

        var filterContext = new FilterContext();
        filterChainProcessor.next(filterContext);

        var expecteds = List.of(
                List.of(filterWithNoPriority0, filterWithNoPriority1),
                List.of(filterWithNoPriority1, filterWithNoPriority0)
        );
        var matchesInAnyOrder = expecteds
                .stream()
                .anyMatch(Predicate.isEqual(called));

        assertTrue(called.size() == 2);
        assertTrue(matchesInAnyOrder);

    }


    @Test
    @DisplayName("Chain should respect filter priority")
    void chainShouldRespectFilterPriority() {

        var called = new ArrayList<Filter>();

        FilterWithPriorityListener priorityFilterListener = (filter, filterContext) -> {
            called.add(filter);
        };
        var filterWithPriority0 = new FilterWithPriority0(priorityFilterListener);
        var filterWithPriority1 = new FilterWithPriority1(priorityFilterListener);

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

        FilterWithPriorityListener priorityFilterListener = (filter, filterContext) -> {
            called.add(filter);
        };
        var filterWithPriority0 = new FilterWithPriority0(false, priorityFilterListener);
        var filterWithPriority1 = new FilterWithPriority1(true, priorityFilterListener);

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

        var filterWithPriority0 = new FilterWithPriority0((filter, filterContext) -> filterContext.put(key, value));
        var filterWithPriority1 = new FilterWithPriority1((filter, filterContext) -> {
            var actual = filterContext.get(key);
            assertEquals(value, actual);
        });

        var filterChainProcessor = new FilterChainProcessor();
        filterChainProcessor.register(filterWithPriority0);
        filterChainProcessor.register(filterWithPriority1);

        var filterContext = new FilterContext();
        filterChainProcessor.next(filterContext);

    }

    interface FilterWithPriorityListener {
        void onNext(final Filter filter, final FilterContext filterContext);
    }


    static abstract class FilterWithPriority implements Filter {
        private final boolean shoulCallNext;
        private final FilterWithPriorityListener filterWithPriorityListener;

        public FilterWithPriority(final boolean shoulCallNext,
                                  final FilterWithPriorityListener filterWithPriorityListener) {
            this.shoulCallNext = shoulCallNext;
            this.filterWithPriorityListener = filterWithPriorityListener;
        }

        @Override
        public void doNext(final FilterContext filterContext,
                           final FilterChain filterChain) {
            filterWithPriorityListener.onNext(this, filterContext);
            if (shoulCallNext) {
                filterChain.next();
            }
        }
    }

    static class FilterWithPriority0 extends FilterWithPriority {

        FilterWithPriority0(final FilterWithPriorityListener filterWithPriorityListener) {
            this(true, filterWithPriorityListener);
        }

        FilterWithPriority0(final boolean shouldCallNext,
                            final FilterWithPriorityListener filterWithPriorityListener) {
            super(shouldCallNext, filterWithPriorityListener);
        }
    }

    @FilterPriority(after = FilterWithPriority0.class)
    static class FilterWithPriority1 extends FilterWithPriority {
        FilterWithPriority1(final FilterWithPriorityListener filterWithPriorityListener) {
            this(true, filterWithPriorityListener);
        }

        FilterWithPriority1(final boolean shouldCallNext,
                            final FilterWithPriorityListener filterWithPriorityListener) {
            super(shouldCallNext, filterWithPriorityListener);
        }
    }


    static class FilterWithNoPriority0 extends FilterWithPriority {
        FilterWithNoPriority0(final FilterWithPriorityListener filterWithPriorityListener) {
            super(true, filterWithPriorityListener);
        }
    }

    static class FilterWithNoPriority1 extends FilterWithPriority {
        FilterWithNoPriority1(final FilterWithPriorityListener filterWithPriorityListener) {
            super(true, filterWithPriorityListener);
        }
    }
}