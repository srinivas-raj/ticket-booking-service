package com.veda.online.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketDetails {
    private String rowId;
    private Integer seatNumber;
}
