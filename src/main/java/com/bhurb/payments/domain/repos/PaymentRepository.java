package com.bhurb.payments.domain.repos;

import com.bhurb.payments.domain.model.entities.payments.Payment;

public interface PaymentRepository {
    Payment save(final Payment customer);
}
