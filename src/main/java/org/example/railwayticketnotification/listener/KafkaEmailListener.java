package org.example.railwayticketnotification.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.railwayticketnotification.dto.message.TicketData;
import org.example.railwayticketnotification.service.intface.TicketMailSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaEmailListener {

    private final TicketMailSender ticketMailSender;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            TicketData ticketData = objectMapper.readValue(message, TicketData.class);
            log.info("Message received -> {}", ticketData);
            ticketMailSender.sendTicketEmail(ticketData);
        } catch (JsonProcessingException e) {
            log.error("Error occurred: {}", e.getMessage());
        } catch (MessagingException e) {
            log.error("Error occurred during send mail: {}", e.getMessage());
        }
    }
}
