package multi_1;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class YandexEmailSender {

    YandexEmailSender(int average) throws MessagingException {
        System.out.println("\n\n");

        Properties props = System.getProperties();
        String host = "smtp.yandex.com";
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", "######");
        props.put("mail.smtp.password", "######");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, null);
//        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");
        message.setSubject("[#] Multi-Agents_Center_Info [#]", "UTF-8");
        message.setText("[#] Hey, Commandos! There is an average here: " + average + " [#]", "UTF-8");

        try {
            message.setFrom(new InternetAddress("######"));
            InternetAddress toAddress = new InternetAddress("######");
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject("[#] Multi-Agents_Center_Info [#]", "UTF-8");
            message.setText("[#] Hey, Commandos! There is an average here: " + average + " [#]", "UTF-8");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, "######", "######");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
        } catch (MessagingException me) {
        }

    }
}
