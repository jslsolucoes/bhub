package com.bhurb.payments.application.payments.chain;

import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class TopologicalSortFiltersTest extends AbstractTest {

    @Test
    @DisplayName("Should detect circular cycle")
    void shouldDetectCircularCycle() {
        var topologicalSortFilters = new TopologicalSortFilters();
        topologicalSortFilters.register(FilterA.class, FilterB.class);
        var illegalArgumentException = assertThrows(IllegalStateException.class, () -> {
            topologicalSortFilters.all();
        });
        var message = illegalArgumentException.getMessage();
        assertTrue(message.startsWith("There is a cycle on the filter sequence:"));
    }

    @Test
    @DisplayName("Should order correctly filters")
    void shouldOrderCorrectlyFilters() {
        var topologicalSortFilters = new TopologicalSortFilters();
        topologicalSortFilters.register(FilterB.class, FilterC.class);
        var sortedFilters = topologicalSortFilters.all();
        var expected = List.of(FilterA.class, FilterB.class, FilterC.class);
        assertEquals(expected, sortedFilters);
    }

    @FilterPriority(after = FilterB.class)
    static class FilterC implements Filter {
        @Override
        public void doNext(final FilterContext filterContext, final FilterChain filterChain) {
            filterChain.next();
        }
    }

    @FilterPriority(after = FilterB.class)
    static class FilterA implements Filter {
        @Override
        public void doNext(final FilterContext filterContext, final FilterChain filterChain) {
            filterChain.next();
        }

    }

    @FilterPriority(after = FilterA.class)
    static class FilterB implements Filter {
        @Override
        public void doNext(final FilterContext filterContext, final FilterChain filterChain) {
            filterChain.next();
        }

    }

}