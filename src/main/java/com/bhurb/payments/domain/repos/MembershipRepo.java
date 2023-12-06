package com.bhurb.payments.domain.repos;

import com.bhurb.payments.domain.model.entities.products.Membership;

import java.util.Optional;

public interface MembershipRepo {
    Optional<Membership> findById(final Long id);
}
