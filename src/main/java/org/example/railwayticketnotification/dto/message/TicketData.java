package org.example.railwayticketnotification.dto.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class TicketData {
    private String passengerName;
    private String passengerEmail;
    private String fileDirectory;
    private String filename;
}
