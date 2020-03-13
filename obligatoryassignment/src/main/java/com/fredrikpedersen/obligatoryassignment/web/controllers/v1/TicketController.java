package com.fredrikpedersen.obligatoryassignment.web.controllers.v1;

import com.fredrikpedersen.obligatoryassignment.service.TicketService;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketDTO;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 20:04
 */

@RestController
@RequestMapping(TicketController.BASE_URL)
@CrossOrigin
public class TicketController {

    public static final String BASE_URL = "/api/v1/tickets/";

    private final TicketService ticketService;

    public TicketController(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TicketListDTO getAllTickets() {
        return new TicketListDTO(ticketService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO createNewTicket(@RequestBody final TicketDTO ticketDTO) {
        return ticketService.createNew(ticketDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTickets() {
        ticketService.deleteAll();
    }
}
