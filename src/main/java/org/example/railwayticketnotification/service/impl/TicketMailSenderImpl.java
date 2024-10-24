package org.example.railwayticketnotification.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.railwayticketnotification.dto.message.TicketData;
import org.example.railwayticketnotification.service.intface.TicketMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketMailSenderImpl implements TicketMailSender {

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${ticket.file.base.directory}")
    private String fileBaseDirectory;

    private final JavaMailSender mailSender;

    @Override
    public void sendTicketEmail(TicketData ticketData) throws MessagingException {
        FileSystemResource resource = new FileSystemResource(
                new File(
                        String.format("%s/%s/%s", fileBaseDirectory, ticketData.getFileDirectory(), ticketData.getFilename())
                )
        );

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(senderEmail);
        mimeMessageHelper.setTo(ticketData.getPassengerEmail());
        String subject = "Download Ticket";
        String content = "<p>Dear, " + ticketData.getPassengerName() + "</p>"
                + "<p>You have successfully purchased BD railway ticket. Please download the ticket from here. "
                + "Do not share your ticket with anyone. Keep a printed copy of your ticket during journey. "
                + "Keep your NID card with you. It is completely illegal to use another person's ticket "
                + "For any kind of help contact to our call centre.</p>"
                + "<p>Thank you for your journey with BD Railway.</p>"
                + "<p>Best Regards,</p>"
                + "<p>System Administrator.</p>";
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        mimeMessageHelper.addAttachment(ticketData.getFilename(), resource);
        mailSender.send(mimeMessage);

        log.info("Email sent successfully to: {}", ticketData.getPassengerEmail());
    }

}
