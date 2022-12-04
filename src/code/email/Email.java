package code.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Email {
    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    public static void sendEmailLetter(String errorInfo){


        try {
            final Properties properties = new Properties();

            properties.load(Email.class.getClassLoader().getResourceAsStream("resources/email/mail.properties"));

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("oleksii.oliinyk.university@ukr.net", "gROkvcDcGaR4BNwz");
                }
            };

            Session mailSession = Session.getDefaultInstance(properties, auth);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("oleksii.oliinyk.university@ukr.net"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("oleksii.oliinyk.university@ukr.net"));
            message.setSubject("Program error");
            message.setText(errorInfo);

            Transport tr = mailSession.getTransport();
            tr.connect(null, "m$48da^makashi@t@do");
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();

        }catch (MessagingException | IOException e){
            logger.severe("Помилка надсилання листа");
        }
    }
}
