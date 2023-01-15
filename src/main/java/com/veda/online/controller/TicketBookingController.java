package com.veda.online.controller;

import com.veda.online.model.BookingType;
import com.veda.online.model.RegDetails;
import com.veda.online.model.RowDetails;
import com.veda.online.model.TicketDetails;
import com.veda.online.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("seats/{id}/lock")
    public String lockTicket(@PathVariable String id,
                             @RequestBody List<TicketDetails> ticketDetails){
        return registrationService.lockTickets(id,ticketDetails);
    }

    @PostMapping("seats/{id}/unlock")
    public String unLockTicket(@PathVariable String id,
                             @RequestBody List<TicketDetails> ticketDetails){
        return registrationService.unLock(id,ticketDetails);
    }

    @PostMapping("seats/{id}/book")
    public String book(@PathVariable String id,
                               @RequestBody List<TicketDetails> ticketDetails){
        return registrationService.bookTicket(id,ticketDetails);
    }
}
