package com.bhurb.payments.domain.model.entities.specs;

import com.bhurb.payments.domain.model.entities.BookPayment;
import com.bhurb.payments.domain.model.entities.VideoPayment;
import com.bhurb.payments.junit.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrSpecTest extends AbstractTest {

    @Test
    @DisplayName("Should return true when all specs are satisfied")
    void shouldReturnTrueWhenOneSpecsAreSatisfied() {
        var isBookSpec = new IsBookSpec();
        var isPhysicalSpec = new IsPhysicalSpec();
        var orSpec = new OrSpec(isBookSpec, isPhysicalSpec);
        var paymentSpec = new BookPayment(null, 1L, "somebook", "somauthor",
                BookPayment.BookType.DIGITAL, null);
        assertTrue(orSpec.isSatisfiedBy(paymentSpec));
    }

    @Test
    @DisplayName("Should return false when all specs are not satisfied")
    void shouldReturnFalseWhenOneSpecIsNotSatisfied() {
        var isBookSpec = new IsBookSpec();
        var isPhysicalSpec = new IsPhysicalSpec();
        var orSpec = new OrSpec(isBookSpec, isPhysicalSpec);
        var paymentSpec = new VideoPayment(null, 1L, "somevideo", null);
        assertFalse(orSpec.isSatisfiedBy(paymentSpec));
    }

}