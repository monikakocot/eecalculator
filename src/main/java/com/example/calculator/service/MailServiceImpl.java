package com.example.calculator.service;

import com.example.calculator.model.Compressor;

import com.example.calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(Object object, Object object1) {

        long result = (long) object;
        User user = (User) object1;

        MimeMessagePreparator preparator = getMessagePreparator(result,user);

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(long result, User user) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                //mimeMessage.setFrom("customerserivces@yourshop.com");
                //mimeMessage.setFrom("monika.kocot9@gmail.com");

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getEmail()));
                mimeMessage.setText("Dear " + user.getName()
                        + ", here we are sending the results of your calculation " + result +" kWh " + ".");
                mimeMessage.setSubject("Money that you can save from modernization!");
            }
        };
        return preparator;
    }





}

//    private final MailProperties mailProperties;
//
//    public MailServiceImpl(MailProperties mailProperties) {
//        this.mailProperties = mailProperties;
//    }
//
//    @Override
//    public boolean sendMail(String subject, String body) {
//        try {
//            Properties props = System.getProperties();
//            props.put("mail.transport.protocol", "smtp");
//            props.put("mail.smtp.port", mailProperties.getSmtp().getPort());
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.auth", "true");
//
//            Session session = Session.getDefaultInstance(props);
//
//            MimeMessage msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(mailProperties.getFrom(), mailProperties.getFromName()));
//            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailProperties.getTo()));
//            msg.setSubject(subject);
//            msg.setContent(body, "text/html");
//
//            Transport transport = session.getTransport();
//            transport.connect(mailProperties.getSmtp().getHost(), mailProperties.getSmtp().getUsername(), mailProperties.getSmtp().getPassword());
//            transport.sendMessage(msg, msg.getAllRecipients());
//            return true;
//
//
//        } catch (Exception ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
//        }
//
//        return false;
//    }