package com.example.eatsdelivery;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;

    private String email, subject, message;

    public JavaMailAPI(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");


        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
                    }
                });

        session.setDebug(true);

        MimeMessage mMessage = new MimeMessage(session);
        try {
            mMessage.setFrom(new InternetAddress(Utils.EMAIL));
            mMessage.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(String.valueOf(new InternetAddress(email)))
            );
            mMessage.setSubject(subject);
            mMessage.setText(message);

            Transport transport = session.getTransport("smtps");
            transport.send(mMessage);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }
}