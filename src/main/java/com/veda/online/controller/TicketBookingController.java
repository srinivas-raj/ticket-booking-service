package com.veda.online.controller;

import com.veda.online.model.BookingType;
import com.veda.online.model.RegDetails;
import com.veda.online.model.RowDetails;
import com.veda.online.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketBookingController {
    private final RegistrationService registrationService;
    @GetMapping("{type}/availability")
    public List<RegDetails> availability(@PathVariable BookingType type){
        return registrationService.finalAllByType(type.name());
    }

    @GetMapping("seats/{id}/availability")
    public List<RowDetails> availability(@PathVariable String id){
        return registrationService.finalAllByTypeId(id);
    }
}
