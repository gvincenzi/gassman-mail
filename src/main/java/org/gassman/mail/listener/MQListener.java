package org.gassman.mail.listener;

import org.gassman.mail.binding.MQBinding;
import org.gassman.mail.dto.OrderDTO;
import org.gassman.mail.dto.RechargeUserCreditLogDTO;
import org.gassman.mail.dto.UserDTO;
import org.gassman.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MQBinding.class)
public class MQListener {
    @Autowired
    MailService mailService;

    @StreamListener(target = MQBinding.USER_REGISTRATION)
    public void processUserRegistration(UserDTO msg) {
        mailService.sendRegistrationMessage(msg);
    }

    @StreamListener(target = MQBinding.USER_ORDER)
    public void processUserOrder(OrderDTO msg) {
        mailService.sendOrderMessage(msg);
    }

    @StreamListener(target = MQBinding.ORDER_PAYMENT_CONFIRMATION)
    public void processOrderPaymentConfirmation(OrderDTO msg) {
        mailService.sendOrderPaymentConfirmationMessage(msg);
    }

    @StreamListener(target = MQBinding.RECHARGE_USER_CREDIT)
    public void processRechargeUserCredit(RechargeUserCreditLogDTO msg) {
        mailService.sendRechargeUserCreditMessage(msg);
    }

    @StreamListener(target = MQBinding.ORDER_NON_PAID_REMINDER)
    public void processOrderNonPaidReminder(OrderDTO msg) {
        mailService.sendOrderNonPaidReminderMessage(msg);
    }

    @StreamListener(target = MQBinding.ORDER_PRODUCT_DELIVERY_REMINDER)
    public void processOrderProductDeliveryReminder(OrderDTO msg) {
        mailService.sendOrderProductDeliveryMessage(msg);
    }

    @StreamListener(target = MQBinding.PRODUCT_UPDATE)
    public void processProductUpdate(OrderDTO msg) {
        mailService.sendProductUpdateMessage(msg);
    }

    @StreamListener(target = MQBinding.ORDER_CANCELLATION)
    public void processOrderCancellation(OrderDTO msg) {
        mailService.sendOrderCancellationMessage(msg);
    }

    @StreamListener(target = MQBinding.USER_CANCELLATION)
    public void processOrderCancellation(UserDTO msg) {
        mailService.sendUserCancellationMessage(msg);
    }
}
