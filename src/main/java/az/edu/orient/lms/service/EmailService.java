package az.edu.orient.lms.service;

import az.edu.orient.lms.entity.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${server.host}")
    private String host;

    @Value("${server.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String ourMailAddress;

    private final String confirmationUrlStarting = "http://" + host + ":" + port + "/api/v1/register/confirm?token=";

    @Async
    public void sendConfirmationEmail(RegistrationRequest registrationRequest) throws MessagingException {
        String confirmationUrl = confirmationUrlStarting.concat(registrationRequest.getRequestToken());

        String subject = "Confirm your registration";
        String message = String.format(
                "<p>Dear %s %s,</p>" +
                        "<p>Thank you for registering. Please click the link below to confirm your registration:</p>" +
                        "<p><a href=\"%s\">Confirm Registration</a></p>",
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                confirmationUrl
        );

        sendEmail(registrationRequest.getEmail(), subject, message);
    }

    @Async
    public void sendWelcomeEmail(RegistrationRequest registrationRequest) throws MessagingException {
        String subject = "Welcome to Our Platform!";
        String message = String.format(
                "<p>Dear %s %s,</p>" +
                        "<p>Your registration is successful! We're glad to have you with us.</p>",
                registrationRequest.getFirstName(),
                registrationRequest.getLastName()
        );

        sendEmail(registrationRequest.getEmail(), subject, message);
    }

    private void sendEmail(String to, String subject, String htmlText) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlText, true);
        helper.setFrom(ourMailAddress);
        mailSender.send(message);
    }
}