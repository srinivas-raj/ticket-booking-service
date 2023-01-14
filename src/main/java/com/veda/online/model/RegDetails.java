package com.veda.online.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegDetails {

    private String typeId;
    private Integer totalSeats;
    private Integer availableSeats;
    private List<RowDetails> rows;
}
