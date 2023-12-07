package com.bhurb.payments.application.payments.chain;


import com.bhurb.payments.domain.model.entities.payments.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentHandlerContext {

    private final Map<String, Object> params;
    private final Payment payment;

    public PaymentHandlerContext(Payment payment) {
        this.payment = payment;
        this.params = new HashMap<>();
    }

    public void put(final String key,
                    final Object value) {
        this.params.put(key, value);
    }

    public Object get(final String key) {
        return this.params.get(key);
    }

    public boolean containsKey(String key) {
        return this.params.containsKey(key);
    }

    public Payment payment() {
        return this.payment;
    }
}
