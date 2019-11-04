package sample;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailManager {

    public static boolean sendMail(String title,String content,String recipient){
        String username = "grupamocnych@gmail.com";
        String password = "GrupaMocnych2019";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    //InternetAddress.parse("agag.gom@gmail.com")
                    InternetAddress.parse(recipient)
            );
            message.setSubject(title);
            message.setContent(content,"text/html");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
