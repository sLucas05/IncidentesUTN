package g6c006.Notifications;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public void Send(String destino, String asunto, String mensaje) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("email", "token");
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("email"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
			message.setSubject(asunto);
			message.setText(mensaje);
			Transport.send(message);
		} catch (MessagingException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
}
