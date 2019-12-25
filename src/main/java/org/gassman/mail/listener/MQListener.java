package org.gassman.mail.listener;

import org.gassman.mail.binding.MQBinding;
import org.gassman.mail.dto.OrderDTO;
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
}
