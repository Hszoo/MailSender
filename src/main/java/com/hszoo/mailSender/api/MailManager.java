package com.hszoo.mailSender.api;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MailManager {
    @Value("${spring.mail.username}")
    private String sendFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String sendTo, String title, String content) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(sendFrom);
        mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(sendTo));
        mimeMessage.setSubject(title);
        mimeMessage.setText(content);
        javaMailSender.send(mimeMessage);
    }

}