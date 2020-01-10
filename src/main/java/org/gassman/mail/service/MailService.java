package org.gassman.mail.service;

import org.gassman.mail.dto.OrderDTO;
import org.gassman.mail.dto.RechargeUserCreditLogDTO;
import org.gassman.mail.dto.UserDTO;

public interface MailService {
    void sendRegistrationMessage(UserDTO userDTO);
    void sendUserCancellationMessage(UserDTO userDTO);
    void sendOrderMessage(OrderDTO orderDTO);
    void sendOrderPaymentConfirmationMessage(OrderDTO orderDTO);
    void sendRechargeUserCreditMessage(RechargeUserCreditLogDTO rechargeUserCreditLogDTO);
    void sendOrderNonPaidReminderMessage(OrderDTO orderDTO);
    void sendOrderProductDeliveryMessage(OrderDTO orderDTO);
    void sendProductUpdateMessage(OrderDTO orderDTO);
    void sendOrderCancellationMessage(OrderDTO orderDTO);
}
