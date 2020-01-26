package org.gassman.mail.service;

import org.gassman.mail.dto.OrderDTO;
import org.gassman.mail.dto.RechargeUserCreditLogDTO;
import org.gassman.mail.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Autowired
    SimpleMailMessage templateCreditRechargeConfirmationMessage;

    @Autowired
    SimpleMailMessage templateReminderOrderNonPaidMessage;

    @Autowired
    SimpleMailMessage templateReminderOrderProductDeliveryMessage;

    @Autowired
    SimpleMailMessage templateProductUpdateMessage;

    @Autowired
    SimpleMailMessage templateUserCancellationMessage;

    @Autowired
    SimpleMailMessage templateOrderCancellationMessage;

    @Value("${template.paymentInternalCreditURL}")
    public String templatePaymentInternalCreditURL;

    @Value("${template.subject.registration}")
    public String templateSubjectRegistration;

    @Value("${template.subject.order}")
    public String templateSubjectOrder;

    @Value("${template.subject.payment}")
    public String templateSubjectPayment;

    @Value("${template.subject.creditrecharge}")
    public String templateSubjectCreditRecharge;

    @Value("${template.subject.reminder.ordernonpaid}")
    public String templateSubjectReminderOrderNonPaid;

    @Value("${template.subject.reminder.delivery}")
    public String templateSubjectReminderOrderProductDelivery;

    @Value("${template.subject.productupdate}")
    public String templateSubjectProductUpdate;

    @Value("${template.subject.usercancellation}")
    public String templateSubjectUserCancellation;

    @Value("${template.subject.ordercancellation}")
    public String templateSubjectOrderCancellation;

    public void sendRegistrationMessage(UserDTO userDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getMail());
        message.setSubject(templateSubjectRegistration);
        message.setText(String.format(templateRegistrationMessage.getText(), userDTO.getSurname(), userDTO.getName(), userDTO.getMail()));
        javaMailSender.send(message);
    }

    @Override
    public void sendUserCancellationMessage(UserDTO userDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getMail());
        message.setSubject(templateSubjectUserCancellation);
        message.setText(String.format(templateUserCancellationMessage.getText(), userDTO.getSurname()));
        javaMailSender.send(message);
    }

    public void sendOrderMessage(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectOrder);
        String paymentInternalCreditURL = String.format(templatePaymentInternalCreditURL,orderDTO.getOrderId()).replaceAll(" ","%20");
        message.setText(String.format(templateOrderMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString(), NumberFormat.getCurrencyInstance().format(new BigDecimal(orderDTO.getQuantity()).multiply(orderDTO.getProduct().getPricePerUnit())),paymentInternalCreditURL));
        javaMailSender.send(message);
    }

    public void sendOrderPaymentConfirmationMessage(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectPayment);
        message.setText(String.format(templatePaymentConfirmationMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString(), NumberFormat.getCurrencyInstance().format(new BigDecimal(orderDTO.getQuantity()).multiply(orderDTO.getProduct().getPricePerUnit()))));
        javaMailSender.send(message);
    }

    @Override
    public void sendRechargeUserCreditMessage(RechargeUserCreditLogDTO rechargeUserCreditLogDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(rechargeUserCreditLogDTO.getUserCredit().getMail());
        message.setSubject(templateSubjectCreditRecharge);
        message.setText(String.format(templateCreditRechargeConfirmationMessage.getText(), rechargeUserCreditLogDTO.getUserCredit().getName(), NumberFormat.getCurrencyInstance().format(rechargeUserCreditLogDTO.getOldCredit()), NumberFormat.getCurrencyInstance().format(rechargeUserCreditLogDTO.getNewCredit()), rechargeUserCreditLogDTO.getRechargeDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
        javaMailSender.send(message);
    }

    @Override
    public void sendOrderNonPaidReminderMessage(OrderDTO orderDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectReminderOrderNonPaid);
        String paymentInternalCreditURL = String.format(templatePaymentInternalCreditURL,orderDTO.getOrderId()).replaceAll(" ","%20");
        message.setText(String.format(templateReminderOrderNonPaidMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString(), NumberFormat.getCurrencyInstance().format(new BigDecimal(orderDTO.getQuantity()).multiply(orderDTO.getProduct().getPricePerUnit())),paymentInternalCreditURL));
        javaMailSender.send(message);
    }

    @Override
    public void sendOrderProductDeliveryMessage(OrderDTO orderDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectReminderOrderProductDelivery);
        message.setText(String.format(templateReminderOrderProductDeliveryMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString()));
        javaMailSender.send(message);
    }

    @Override
    public void sendProductUpdateMessage(OrderDTO orderDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectProductUpdate);
        message.setText(String.format(templateProductUpdateMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString()));
        javaMailSender.send(message);
    }

    @Override
    public void sendOrderCancellationMessage(OrderDTO orderDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderDTO.getUser().getMail());
        message.setSubject(templateSubjectOrderCancellation);
        message.setText(String.format(templateOrderCancellationMessage.getText(), orderDTO.getUser().getName(), orderDTO.toString()));
        javaMailSender.send(message);
    }

}
