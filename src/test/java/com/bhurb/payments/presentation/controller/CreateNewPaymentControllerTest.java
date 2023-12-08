package com.bhurb.payments.presentation.controller;

import com.bhurb.payments.junit.AbstractMvcIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateNewPaymentControllerTest extends AbstractMvcIntegrationTest {


    @Test
    @DisplayName("should refuse new video payment if video name is empty")
    void shouldProcessRefuseNewVideoPaymentIfVideoNameIsEmpty() throws Exception {
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
                          "type": "video",
                          "id": 1
                        }
                      }
                """;
        var requestBuilder = post(ApiInfo.API_V1 + "/payments")
                .content(json)
                .with(noAuthentication());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", hasKey("messages")))
                .andExpect(jsonPath("$.messages", hasSize(1)))
                .andExpect(jsonPath("$.messages[0]").value("payload.name must not be blank"));;

    }

    @Test
    @DisplayName("should process new video payment successfully")
    void shouldProcessNewVideoPaymentSuccessfully() throws Exception {
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
                          "type": "video",
                          "id": 1,
                          "name": "The Lord of the Rings"
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

    @Test
    @DisplayName("should process upgrade membership payment successfully")
    void shouldProcessUpgradeMembershipPaymentSuccessfully() throws Exception {
        var json = """
                    {
                        "productId": 1,
                        "createdAt": "2020-01-01T00:00:00Z",
                        "amount": 1000.00,
                        "customerId": 1,
                        "customerName": "John Doe",
                        "customerEmail": "jon@jon.com",
                        "customerCurrentMembershipPlan": "BASIC",
                        "sellerId": 1,
                        "sellerName": "Jane Doe",
                        "payload": {
                          "type": "membership",
                          "id": 1,
                          "membershipPlan": "PREMIUM"
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

    @Test
    @DisplayName("should process new membership payment successfully")
    void shouldProcessNewMembershipPaymentSuccessfully() throws Exception {
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
                          "type": "membership",
                          "id": 1,
                          "membershipPlan": "BASIC"
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

//LogPaymentHandler -> NewMembershipPaymentHandler -> DeliveryDocPaymentHandler -> UpgradeMembershipPaymentHandler -> RoyaltDeliveryDocPaymentHandler -> IsVideoLawEnforcement1997PaymentHandler -> ComissionPaymentHandler -> FinishPaymentHandler