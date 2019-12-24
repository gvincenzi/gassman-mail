package org.gassman.mail.service;

import org.gassman.mail.dto.OrderDTO;
import org.gassman.mail.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage templateRegistrationMessage;

    @Autowired
    SimpleMailMessage templateOrderMessage;

    @Value("${template.paymentURL}")
    public String templatePaymentURL;

    public void sendRegistrationMessage(UserDTO userDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getMail());
        message.setSubject("GasSMan Registration");
        message.setText(String.format(templateRegistrationMessage.getText(), userDTO.getSurname(), userDTO.getName(), userDTO.getMail()));
        javaMailSender.send(message);
    }

    public void sendOrderMessage(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject("GasSMan Order");
        String paymentURL = String.format(templatePaymentURL,orderDTO.toHTTPQuery()).replaceAll(" ","%20");
        message.setText(String.format(templateOrderMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString(), NumberFormat.getCurrencyInstance().format(new BigDecimal(orderDTO.getQuantity()).multiply(orderDTO.getProduct().getPricePerUnit())),paymentURL));
        javaMailSender.send(message);
    }

}
