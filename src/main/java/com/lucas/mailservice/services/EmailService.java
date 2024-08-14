package com.lucas.mailservice.services;

import java.util.Date;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String from;

    public void sendReport(String content, String to) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            int currentMonth = new Date().getMonth();

            helper.setFrom(from);
            helper.setText(content, true);
            helper.setSubject("Monthly Report = " + currentMonth);
            helper.setTo(to);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
