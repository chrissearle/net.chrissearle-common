package net.chrissearle.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import java.util.logging.Level;

@Service
public class SimpleMailMessageManager implements SimpleMailService {
    Logger logger = Logger.getLogger(SimpleMailMessageManager.class.getName());

    private MailSender mailSender;
    private SimpleMailMessage mailTemplate;

    public SimpleMailMessageManager(MailSender mailSender, SimpleMailMessage mailTemplate) {
        this.mailSender = mailSender;
        this.mailTemplate = mailTemplate;
    }

    public void sendPost(String title, String text) {
        sendPost(mailTemplate.getTo(), title, text);
    }

    public void sendPost(String to, String title, String text) {
        String[] toAddresses = new String[1];
        toAddresses[0] = to;

        sendPost(to, title, text);
    }

    protected void sendPost(String to[], String title, String text) {
        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(mailTemplate);

        msg.setSubject(title);
        msg.setText(text);

        try {
            this.mailSender.send(msg);
        } catch (MailException ex) {
            // We log this - it's not supposed to show up to the user - but should show in the system logs
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Unable to send post mail: " + ex.getMessage());
            }
        }
    }
}
