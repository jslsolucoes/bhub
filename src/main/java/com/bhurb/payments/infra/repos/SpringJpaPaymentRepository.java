package com.bhurb.payments.infra.repos;

import com.bhurb.payments.domain.model.entities.Payment;
import com.bhurb.payments.domain.repos.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJpaPaymentRepository extends PaymentRepository, JpaRepository<Payment, Long> {

}
