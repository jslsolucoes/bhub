package com.bhurb.payments.infra.repos;

import com.bhurb.payments.domain.model.entities.products.Membership;
import com.bhurb.payments.domain.repos.MembershipRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringVideoJpaRepository extends MembershipRepo, JpaRepository<Membership, Long> {
}
