package com.klef.jfsd.handloom_products_backend.service;

import com.klef.jfsd.handloom_products_backend.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;  // Inject JavaMailSender

    // Send a simple order confirmation email (useful for basic notifications)
    public void sendOrderConfirmation(String toEmail, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);
            
            // Send the email
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Log or handle the exception
        }
    }

    // Send detailed order confirmation email with cart items and total amount
    public void sendOrderDetailsEmail(String toEmail, List<Cart> cartItems, Double totalAmount) {
        String subject = "Order Confirmation - Thank You for Your Purchase!";
        StringBuilder body = new StringBuilder("Dear Customer,\n\nThank you for your order!\n\n");
        
        // Adding cart items to the email body
       
        body.append(String.format("\nTotal Amount: ₹%.2f\n\n", totalAmount));
        body.append("We hope you enjoy your purchase. Please let us know if you have any questions.\n\nBest regards,\nHandloom Products Team");

        // Use the helper method to send the email
        sendEmail(toEmail, subject, body.toString());
    }

    // Helper method to send an email
    private void sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(body);
            
            // Send the email
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exceptions as needed
        }
    }
}
