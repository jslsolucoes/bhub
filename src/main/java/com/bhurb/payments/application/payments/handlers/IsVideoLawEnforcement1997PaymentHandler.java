package com.bhurb.payments.application.payments.handlers;

import com.bhurb.payments.application.payments.chain.PaymentHandler;
import com.bhurb.payments.application.payments.chain.PaymentHandlerChain;
import com.bhurb.payments.application.payments.chain.PaymentHandlerContext;
import com.bhurb.payments.application.payments.chain.PaymentHandlerPriority;
import com.bhurb.payments.domain.model.entities.DeliveryDocItem;
import com.bhurb.payments.domain.model.entities.specs.IsVideoLawEnforcement1997Spec;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@PaymentHandlerPriority(after = {RoyaltDeliveryDocPaymentHandler.class})
@Component
public class IsVideoLawEnforcement1997PaymentHandler implements PaymentHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(IsVideoLawEnforcement1997PaymentHandler.class);

    @Override
    public void doNext(final PaymentHandlerContext paymentHandlerContext,
                       final PaymentHandlerChain paymentHandlerChain) {
        var payment = paymentHandlerContext.payment();
        var product = payment.product();
        var isVideoLawEnforcement1997Spec = new IsVideoLawEnforcement1997Spec();
        if (isVideoLawEnforcement1997Spec.isSatisfiedBy(product)) {
            LOGGER.debug("Adding free video to delivery doc");
            var primeirosSocorros = FreeVideo.PRIMEIROS_SOCORROS
                    .toDeliveryDocItem();
            payment.addItemToDeliveryDoc(primeirosSocorros);
        }
        paymentHandlerChain.next();
    }

    enum FreeVideo {
        PRIMEIROS_SOCORROS(157L, "Primeiros Socorros");

        private final Long refId;
        private final String name;

        FreeVideo(final Long refId, final String name) {
            this.refId = refId;
            this.name = name;
        }

        public DeliveryDocItem toDeliveryDocItem() {
            return new DeliveryDocItem(null, refId, name, true, null);
        }
    }

}
