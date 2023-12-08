package com.bhurb.payments.domain.model.entities.specs;

import com.bhurb.payments.domain.model.entities.BookPayment;
import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AndSpecTest extends AbstractTest {

    @Test
    @DisplayName("Should return true when all specs are satisfied")
    void shouldReturnTrueWhenAllSpecsAreSatisfied() {
        var isBookSpec = new IsBookSpec();
        var isPhysicalSpec = new IsPhysicalSpec();
        var andSpec = new AndSpec(isBookSpec, isPhysicalSpec);
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.PHYSICAL, null);
        assertTrue(andSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when one spec is not satisfied")
    void shouldReturnFalseWhenOneSpecIsNotSatisfied() {
        var isBookSpec = new IsBookSpec();
        var isPhysicalSpec = new IsPhysicalSpec();
        var andSpec = new AndSpec(isBookSpec, isPhysicalSpec);
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.DIGITAL, null);
        assertFalse(andSpec.isSatisfiedBy(paymentSpec));
    }

}