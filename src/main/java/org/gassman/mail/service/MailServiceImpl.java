package org.gassman.mail.service;

import org.gassman.mail.dto.OrderDTO;
import org.gassman.mail.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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

    @Autowired
    SimpleMailMessage templatePaymentConfirmationMessage;

    @Value("${template.paymentPayPalURL}")
    public String templatePaymentPayPalURL;

    @Value("${template.paymentInternalCreditURL}")
    public String templatePaymentInternalCreditURL;

    @Value("${template.subject.registration}")
    public String templateSubjectRegistration;

    @Value("${template.subject.order}")
    public String templateSubjectOrder;

    @Value("${template.subject.payment}")
    public String templateSubjectPayment;

    public void sendRegistrationMessage(UserDTO userDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getMail());
        message.setSubject(templateSubjectRegistration);
        message.setText(String.format(templateRegistrationMessage.getText(), userDTO.getSurname(), userDTO.getName(), userDTO.getMail()));
        javaMailSender.send(message);
    }

    public void sendOrderMessage(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectOrder);
        String paymentPayPalURL = String.format(templatePaymentPayPalURL,orderDTO.toHTTPQuery()).replaceAll(" ","%20");
        String paymentInternalCreditURL = String.format(templatePaymentInternalCreditURL,orderDTO.toHTTPQuery()).replaceAll(" ","%20");
        message.setText(String.format(templateOrderMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString(), NumberFormat.getCurrencyInstance().format(new BigDecimal(orderDTO.getQuantity()).multiply(orderDTO.getProduct().getPricePerUnit())),paymentInternalCreditURL,paymentPayPalURL));
        javaMailSender.send(message);
    }

    public void sendOrderPaymentConfirmationMessage(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectPayment);
        message.setText(String.format(templatePaymentConfirmationMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString(), NumberFormat.getCurrencyInstance().format(new BigDecimal(orderDTO.getQuantity()).multiply(orderDTO.getProduct().getPricePerUnit()))));
        javaMailSender.send(message);
    }

}
