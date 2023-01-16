package com.veda.online.model;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {
    private List<TicketDetails> ticketDetailsList;
    private Payment payment;
}
