package com.martin.service;

import com.martin.persistence.dao.DynamoDBAccountsDao;
import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class NotificationService {

    private static final Logger LOG = Logger.getLogger(NotificationService.class);

    private static final DynamoDBAccountsDao accountsDao = DynamoDBAccountsDao.instance();

    @Inject
    public JavaMailSender emailSender;

    public void notif() {

        String merchantEmail = "a@gmail.com";

        String subject = setSubjectText();
        String message = setMessageText();

        LOG.info(String.format("Email subject: %s \n body: %s", subject, message));

        sendSimpleMessage(merchantEmail, subject, message);
    }

    private static String setSubjectText() {
        return "SUBJECT";
    }
    private static String setMessageText() {
        return "BODY";
    }

    private void sendSimpleMessage(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        emailSender.send(simpleMailMessage);
    }

}
