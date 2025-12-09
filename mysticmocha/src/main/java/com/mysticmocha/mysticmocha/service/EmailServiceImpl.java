
package com.mysticmocha.mysticmocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {

        String from = System.getenv("MAIL_MYSTICMOCHA_USER"); 

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);                    
        message.setTo("chumelol2015@gmail.com");
        message.setSubject("Teste predo");
        message.setText("Teste funciona pelo amor de Deus");

        emailSender.send(message);
    }
}
