package com.veda.online.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RowDetails {
    private String rowId;
    private List<Seat> seats;
}
