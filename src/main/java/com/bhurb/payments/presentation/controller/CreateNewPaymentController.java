package com.bhurb.payments.presentation.controller;

import com.bhurb.payments.application.payments.PaymentProcessor;
import com.bhurb.payments.domain.model.entities.*;
import com.bhurb.payments.domain.model.entities.MembershipPayment.MembershipPlan;
import com.bhurb.payments.domain.model.valueobject.Email;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(ApiInfo.API_V1)
@Validated
public class CreateNewPaymentController {

    private final PaymentProcessor paymentProcessor;

    @Autowired
    public CreateNewPaymentController(final PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @Operation(summary = "Schedule payments for processing", tags = {ApiInfo.Tags.PAYMENTS, ApiInfo.Tags.PUBLIC})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiInfo.StatusCode.OK),
            @ApiResponse(responseCode = ApiInfo.StatusCode.BAD_REQUEST),
    })
    @PostMapping("/payments")
    public PaymentResponse createNewPayment(@RequestBody @Valid final PaymentRequest paymentRequest) {
        var payment = paymentRequest.asPayment();
        var paymentProcessorInput = new PaymentProcessor.PaymentProcessorInput(payment);
        var paymentProcessorOutput = paymentProcessor.process(paymentProcessorInput);
        return new PaymentResponse(paymentProcessorOutput.paymentId());
    }

    @Schema(name = "PaymentRequest")
    public record PaymentRequest(@NotNull Long customerId,
                                 @NotNull String customerEmail,
                                 @NotBlank String customerName,
                                 MembershipPlan customerCurrentMembershipPlan,
                                 @NotNull Long sellerId,
                                 @NotNull String sellerName,
                                 @NotNull LocalDateTime createdAt,
                                 @NotNull BigDecimal amount,
                                 @Valid @NotNull Payload payload) {


        public Payment asPayment() {

            var productPayment = switch (payload) {
                case Book book -> {
                    yield new BookPayment(
                            null,
                            book.id(),
                            book.name(),
                            book.author(),
                            book.bookType(),
                            null
                    );
                }
                case Membership membership -> {
                    yield new MembershipPayment(
                            null,
                            membership.id(),
                            membership.membershipPlan(),
                            null
                    );
                }
                case Video video -> {
                    yield new VideoPayment(
                            null,
                            video.id(),
                            video.name(),
                            null
                    );
                }
                default -> throw new IllegalArgumentException("Invalid payload type");
            };
            var customer = new CustomerPayment(
                    null,
                    customerId,
                    customerName,
                    new Email(customerEmail),
                    customerCurrentMembershipPlan,
                    null
            );
            var seller = new SellerPayment(
                    null,
                    sellerId,
                    sellerName,
                    null
            );
            return new Payment(
                    null,
                    createdAt,
                    customer,
                    amount,
                    productPayment,
                    seller
            );
        }

        @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
        @JsonSubTypes({
                @JsonSubTypes.Type(value = Book.class, name = "book"),
                @JsonSubTypes.Type(value = Membership.class, name = "membership"),
                @JsonSubTypes.Type(value = Video.class, name = "video")
        })
        interface Payload {
        }

        record Book(@NotNull @JsonProperty("id") Long id,
                    @NotBlank @JsonProperty("name") String name,
                    @NotBlank @JsonProperty("author") String author,
                    @NotNull BookPayment.BookType bookType) implements Payload {

        }

        record Membership(@NotNull @JsonProperty("id") long id,
                          @NotNull MembershipPlan membershipPlan) implements Payload {

        }

        record Video(@NotNull @JsonProperty("id") long id,
                     @NotBlank @JsonProperty("name") String name) implements Payload {

        }

    }

    @Schema(name = "PaymentResponse")
    public record PaymentResponse(Long id) {

    }

}
