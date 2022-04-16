package com.example.demoOTP.Config;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Component
public class SendEmail {

    private static String USER_NAME = "idearyoujei@gmail.com";
    private static String PASSWORD = "29072560";

    public String SendEmail(String RECIPIENT , String subject , String text_body){

        String from1 = USER_NAME;
        String pass1 = PASSWORD;
        String[] to1 = {RECIPIENT}; // list of recipient email addresses
        String subject1 = subject;
        String body = text_body;

        try{
            sendFromGMail(from1, pass1, to1, subject1, body);
        }catch(MessagingException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }


    private void sendFromGMail(String from1, String pass1, String[] to1, String subject1, String body) throws MessagingException {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from1);
        props.put("mail.smtp.password", pass1);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from1));
            InternetAddress[] toAddress = new InternetAddress[to1.length];

            // To get the array of addresses
            for (int i = 0; i < to1.length; i++) {
                toAddress[i] = new InternetAddress(to1[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject1);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from1, pass1);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException ae) {
            ae.printStackTrace();

        }
    }
}
