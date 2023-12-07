
package com.bhurb.payments.application.payments.chain;


import java.util.HashMap;
import java.util.Map;

public class FilterContext {

    private final Map<String, Object> params = new HashMap<>();

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
}
