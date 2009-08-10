package net.chrissearle.mail;

/**
 * Provides a simplified interface for sending mail.
 */
public interface SimpleMailService {
    /**
     * Send a message - assumes that recipient and sender is pre-defined
     * @param title - the mail subject
     * @param text - the mail body
     */
    void sendPost(String title, String text);

    /**
     * Send a message - assumes that recipient is pre-defined
     * @param to - the recipient
     * @param title - the mail subject
     * @param text - the mail body
     */
    void sendPost(String to, String title, String text);
}
