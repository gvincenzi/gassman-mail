package org.gassman.mail.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailTemplateConfiguration{

    @Value("${template.registration}")
    public String templateRegistration;

    @Value("${template.order}")
    public String templateOrder;

    @Value("${template.paymentConfirmation}")
    public String templatePaymentConfirmation;

    @Value("${mail.username}")
    public String mailUsername;

    @Value("${mail.password}")
    public String mailPassword;

    @Value("${mail.host}")
    public String mailHost;

    @Bean
    public SimpleMailMessage templateRegistrationMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(templateRegistration);
        return message;
    }

    @Bean
    public SimpleMailMessage templateOrderMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(templateOrder);
        return message;
    }

    @Bean
    public SimpleMailMessage templatePaymentConfirmationMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(templatePaymentConfirmation);
        return message;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(587);

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
