package com.ticketmagpie.infrastructure;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailService {

  public static final Properties SESSION_PROPERTIES = new Properties();

  static {
    SESSION_PROPERTIES.put("mail.smtp.auth", "true");
    SESSION_PROPERTIES.put("mail.smtp.starttls.enable", "true");
    SESSION_PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
    SESSION_PROPERTIES.put("mail.smtp.port", "587");
  }

  public static final String USERNAME = "ticketmagpie@gmail.com";
  public static final String PASSWORD = "t1ck3tm4gp1e1";
  public static final Authenticator AUTHENTICATOR = new Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(USERNAME, PASSWORD);
    }
  };

  public void sendEmail(String to, String subject, String body) {
    Session session = Session.getInstance(SESSION_PROPERTIES, AUTHENTICATOR);

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(USERNAME));
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(to));
      message.setSubject(subject);
      message.setText(body);

      Transport.send(message);

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
