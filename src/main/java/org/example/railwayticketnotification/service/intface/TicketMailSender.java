package org.example.railwayticketnotification.service.intface;

import jakarta.mail.MessagingException;
import org.example.railwayticketnotification.dto.message.TicketData;

public interface TicketMailSender {
    void sendTicketEmail(TicketData ticketData) throws MessagingException;
}
