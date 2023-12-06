package com.bhurb.payments.presentation.controller;

import com.bhurb.payments.domain.events.EventDispatcher;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(ApiInfo.API_V1)
@Validated
public class CreateNewPaymentController {

    private final EventDispatcher eventDispatcher;

    @Autowired
    public CreateNewPaymentController(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Operation(summary = "Schedule payments for processing", tags = {ApiInfo.Tags.PAYMENTS, ApiInfo.Tags.PUBLIC})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiInfo.StatusCode.OK),
            @ApiResponse(responseCode = ApiInfo.StatusCode.BAD_REQUEST),
    })
    @PostMapping("/payments")
    public PaymentResponse createNewPayment(@RequestBody @Valid final PaymentRequest paymentRequest) {
        eventDispatcher.sendMail("aa@aa.com", "teste", "asd");
        return new PaymentResponse();
    }

    @Schema(name = "PaymentRequest")
    public record PaymentRequest(@NotNull Long productId, @NotNull LocalDateTime createdAt,
                                 @Valid @NotNull Payload payload) {


        @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
        @JsonSubTypes({
                @JsonSubTypes.Type(value = Book.class, name = "book"),
                @JsonSubTypes.Type(value = Membership.class, name = "membership"),
                @JsonSubTypes.Type(value = Video.class, name = "video")
        })
        interface Payload {
        }

        record Book(@NotNull @JsonProperty("id") long id) implements Payload {

        }

        record Membership(@NotNull @JsonProperty("id") long id) implements Payload {

        }

        record Video(@NotNull @JsonProperty("id") long id) implements Payload {

        }

    }

    @Schema(name = "PaymentResponse")
    public record PaymentResponse() {

    }

}
