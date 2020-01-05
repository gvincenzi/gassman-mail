package org.gassman.mail.service;

import org.gassman.mail.dto.OrderDTO;
import org.gassman.mail.dto.RechargeUserCreditLogDTO;
import org.gassman.mail.dto.UserDTO;

public interface MailService {
    void sendRegistrationMessage(UserDTO userDTO);
    void sendOrderMessage(OrderDTO orderDTO);
    void sendOrderPaymentConfirmationMessage(OrderDTO orderDTO);
    void sendRechargeUserCreditMessage(RechargeUserCreditLogDTO msg);
}
