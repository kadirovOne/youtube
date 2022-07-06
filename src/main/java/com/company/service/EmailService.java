package com.company.service;

import com.company.entity.EmailHistoryEntity;
import com.company.repository.EmailRepository;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromAccount;
    public void sendRegistration(String email, Integer id) {
        StringBuilder builder = new StringBuilder();
        builder.append("<h1 style='color:black;font-size:40px;fontFamily:cursive;'>Assalomu Alaykum Youtube_Demo platformasida ro'yhatdan o'tish uchun quyidagi Linkni bosing</h1>");
        builder.append("<p> <a href='http://localhost:8080/auth/email/verification/" +
                JwtUtil.encode(id) + "'>Link verification</a> </p>");
        sendEmail(email, "Registration", builder.toString());

        EmailHistoryEntity entity1 = new EmailHistoryEntity();
        entity1.setEmail(email);

        emailRepository.save(entity1);
    }
    public void sendForUpdateEmail(String email) {
        StringBuilder builder = new StringBuilder();
        builder.append("<h1 style='color:black;font-size:40px;fontFamily:cursive;'>Assalomu Alaykum youtube_demo platformasida o'z Emailingizni o'zgartirish uchun quyidagi Linkni bosing</h1>");
        builder.append("<p> <a href='http://localhost:8080/auth/email/updateEmail/" +
                email + "'>Link verification</a> </p>");
        sendEmail(email, "Registration", builder.toString());

        EmailHistoryEntity entity1 = new EmailHistoryEntity();
        entity1.setEmail(email);

        emailRepository.save(entity1);
    }
    private void sendEmail(String toAccount, String subject, String text) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom(fromAccount);
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(msg);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
