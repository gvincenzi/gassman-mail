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
    String ORDER_NON_PAID_REMINDER = "orderNonPaidReminderChannel";
    String ORDER_PRODUCT_DELIVERY_REMINDER = "orderProductDeliveryReminderChannel";
    String PRODUCT_UPDATE = "productUpdateChannel";
    String USER_CANCELLATION = "userCancellationChannel";
    String ORDER_CANCELLATION = "orderCancellationChannel";

    @Input(USER_REGISTRATION)
    SubscribableChannel userRegistrationChannel();

    @Input(USER_ORDER)
    SubscribableChannel userOrderChannel();

    @Input(ORDER_PAYMENT_CONFIRMATION)
    SubscribableChannel orderPaymentConfirmationChannel();

    @Input(RECHARGE_USER_CREDIT)
    SubscribableChannel rechargeUserCreditChannel();

    @Input(ORDER_NON_PAID_REMINDER)
    SubscribableChannel orderNonPaidReminderChannel();

    @Input(ORDER_PRODUCT_DELIVERY_REMINDER)
    SubscribableChannel orderProductDeliveryReminderChannel();

    @Input(PRODUCT_UPDATE)
    SubscribableChannel productUpdateChannel();

    @Input(USER_CANCELLATION)
    SubscribableChannel userCancellationChannel();

    @Input(ORDER_CANCELLATION)
    SubscribableChannel orderCancellationChannel();
}
