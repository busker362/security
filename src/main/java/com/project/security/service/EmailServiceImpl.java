package com.project.security.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("notstone362@gmail.com");
        mailSender.send(message);
    }

    @Override
    public String sendAuthCode(String to) {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        String authCode = String.valueOf(code);

        String subject = "로그인 코드";
        String text = "인증 번호: " + authCode;
        sendSimpleMessage(to, subject, text);

        return authCode;
    }
}
