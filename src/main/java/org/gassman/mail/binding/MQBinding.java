package org.gassman.mail.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MQBinding {
    String USER_REGISTRATION = "userRegistrationChannel";
    String USER_ORDER = "userOrderChannel";
    String ORDER_PAYMENT_CONFIRMATION = "orderPaymentConfirmationChannel";
    String RECHARGE_USER_CREDIT = "rechargeUserCreditChannel";

    @Input(USER_REGISTRATION)
    SubscribableChannel userRegistrationChannel();

    @Input(USER_ORDER)
    SubscribableChannel userOrderChannel();

    @Input(ORDER_PAYMENT_CONFIRMATION)
    SubscribableChannel orderPaymentConfirmationChannel();

    @Input(RECHARGE_USER_CREDIT)
    SubscribableChannel rechargeUserCreditChannel();
}
