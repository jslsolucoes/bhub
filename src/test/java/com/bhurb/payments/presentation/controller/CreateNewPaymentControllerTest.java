package com.bhurb.payments.presentation.controller;

import com.bhurb.payments.junit.AbstractMvcIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateNewPaymentControllerTest extends AbstractMvcIntegrationTest {

    @Test
    @DisplayName("should process book payment successfully")
    void shouldProcessBookPaymentSuccessfully() throws Exception {
        var json = """
                    {
                        "productId": 1,
                        "createdAt": "2020-01-01T00:00:00Z",
                        "amount": 1000.00,
                        "customerId": 1,
                        "customerName": "John Doe",
                        "customerEmail": "jon@jon.com",
                        "sellerId": 1,
                        "sellerName": "Jane Doe",
                        "payload": {
                          "type": "book",
                          "id": 1,
                          "name": "The Lord of the Rings",
                          "author": "J. R. R. Tolkien",
                          "bookType": "BOTH"
                        }
                      }
                """;
        var requestBuilder = post(ApiInfo.API_V1 + "/payments")
                .content(json)
                .with(noAuthentication());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$.id").value(greaterThan(0)));
    }
}