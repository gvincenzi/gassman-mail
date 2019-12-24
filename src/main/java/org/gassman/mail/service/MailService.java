package org.gassman.mail.service;

import org.gassman.mail.dto.OrderDTO;
import org.gassman.mail.dto.UserDTO;

import java.io.UnsupportedEncodingException;

public interface MailService {
    void sendRegistrationMessage(UserDTO userDTO);
    void sendOrderMessage(OrderDTO orderDTO);
}
