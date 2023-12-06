package com.bhurb.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiInfo.API_V1)
@Validated
public class CreateNewPaymentController {


    @Operation(summary = "Schedule payments for processing", tags = {ApiInfo.Tags.PAYMENTS, ApiInfo.Tags.PUBLIC})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiInfo.StatusCode.OK),
            @ApiResponse(responseCode = ApiInfo.StatusCode.BAD_REQUEST),
    })
    @PostMapping("/payments")
    public PaymentResponse createNewPayment(@RequestBody @Valid final PaymentRequest paymentRequest) {

        return new PaymentResponse();
    }

    @Schema(name = "PaymentRequest")
    public record PaymentRequest(long productId) {
    }

    @Schema(name = "PaymentResponse")
    public record PaymentResponse() {

    }
}
