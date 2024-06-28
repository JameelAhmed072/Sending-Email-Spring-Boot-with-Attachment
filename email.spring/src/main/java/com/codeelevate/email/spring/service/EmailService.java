package com.codeelevate.email.spring.service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @PostConstruct
    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("ahmedjameel072@gmail.com");
        mimeMessageHelper.setTo("engr.jameelahmed2@gmail.com");
        mimeMessageHelper.setText("Hello, This is body");
        mimeMessageHelper.setSubject("This is the subject");


        FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\\Email-Sending-in-SpringBoot\\email.spring\\profile.jpg"));
        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail Send");
    }

}
