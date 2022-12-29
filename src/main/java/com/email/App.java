package com.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        Scanner s=new Scanner(System.in);
        System.out.print("Enter your message:");
        String message=s.nextLine();
        System.out.print("Enter the Subject:");
        String subject=s.nextLine();
        System.out.print("Enter the recipient's e-mail id:");
        String emailSendTo=s.nextLine();
        String emailSendFrom="priyanshuaec2024@gmail.com";
        sendEmail(message,subject,emailSendFrom,emailSendTo);
        sendAttachment(message,subject,emailSendFrom,emailSendTo);
    }

    private static void sendAttachment(String message, String subject, String emailSendFrom, String emailSendTo) {
        //variable for gmail
        String host="smtp.gmail.com";

        //system properties
        Properties properties=System.getProperties();
        System.out.println("Properties "+properties);

        //host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step-1:get the session object
        Session session=Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("priyanshuaec2024@gmail.com","Priy@nshu2024");

                    }
        });session.setDebug(true);

        //step-2: composing the email
        MimeMessage m=new MimeMessage(session);
        try{
            //from email
            m.setFrom(emailSendFrom);

            //adding message
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(emailSendTo));

            //adding the subject
            m.setSubject(subject);

            //attaching the file
            //file path
            String path="C:\\Users\\asus\\Downloads\\IMG_8315.JPG";

            MimeMultipart mimeMultipart=new MimeMultipart();

            MimeBodyPart textMime=new MimeBodyPart();//for text message
            MimeBodyPart fileMime=new MimeBodyPart();//for attachments 

            try {
                textMime.setText(message);
                File file=new File(path);
                fileMime.attachFile(file);
                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);
            }catch (Exception e){
                e.printStackTrace();
            }

            m.setContent(mimeMultipart);

            //Step-3:Sending the message
            Transport.send(m);
            System.out.println("e-Mail sent successfully!!!!!!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //This method is used to send the text email
    private static void sendEmail(String message, String subject, String emailSendFrom, String emailSendTo) {

        //variable for gmail
        String host="smtp.gmail.com";

        //system properties
        Properties properties=System.getProperties();
        System.out.println("Properties "+properties);

        //host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step-1:get the session object
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("priyanshuaec2024@gmail.com","Priy@nshu2024");

            }
        });session.setDebug(true);

        //Step-2:compose message[text,media]
        MimeMessage m=new MimeMessage(session);
        try{
            //from email
            m.setFrom(emailSendFrom);

            //adding message
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(emailSendTo));

            //add subject of the message
            m.setSubject(subject);

            //add text message
            m.setText(message);

            //Step-3:Sending the message
            Transport.send(m);
            System.out.println("e-Mail sent successfully!!!!!!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
