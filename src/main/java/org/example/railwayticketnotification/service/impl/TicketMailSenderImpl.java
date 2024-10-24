package org.example.railwayticketnotification.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.railwayticketnotification.dto.message.TicketData;
import org.example.railwayticketnotification.service.intface.TicketMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketMailSenderImpl implements TicketMailSender {

    @Value("${spring.mail.username}")
    private String senderEmail;

    private final JavaMailSender mailSender;

    @Override
    public void sendTicketEmail(TicketData ticketData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(senderEmail);
        helper.setTo(ticketData.getPassengerEmail());
        String subject = "Download Ticket";
        String content = "<p>Dear, " + ticketData.getPassengerName() + "</p>"
                + "<p>You have successfully purchased BD railway ticket. Please download the ticket from here. "
                + "Do not share your ticket with anyone. Keep a printed copy of your ticket during journey. "
                + "Keep your NID card with you. It is completely illegal to use another person's ticket "
                + "For any kind of help contact to our call centre.</p>"
                + "<p>Thank you for your journey with BD Railway.</p>"
                + "<p>Best Regards,</p>"
                + "<p>System Administrator.</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);

        log.info("Email sent successfully to: {}", ticketData.getPassengerEmail());
    }

}
