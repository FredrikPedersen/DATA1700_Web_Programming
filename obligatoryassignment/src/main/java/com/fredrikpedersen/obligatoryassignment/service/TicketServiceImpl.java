package com.fredrikpedersen.obligatoryassignment.service;

import com.fredrikpedersen.obligatoryassignment.domain.models.Ticket;
import com.fredrikpedersen.obligatoryassignment.repository.TicketRepository;
import com.fredrikpedersen.obligatoryassignment.web.controllers.v1.TicketController;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketDTO;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.mappers.TicketMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Ticket objects utilizing lists and DTO objects as return values
 *
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 20:22
 */

@Service
public class TicketServiceImpl implements TicketService {

    private final String URL = TicketController.BASE_URL;
    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(final TicketMapper ticketMapper, final TicketRepository ticketRepository) {
        this.ticketMapper = ticketMapper;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<TicketDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticket -> {
                    TicketDTO ticketDTO = ticketMapper.ticketToTicketDTO(ticket);
                    ticketDTO.setUrl(URL + ticket.getId());
                    return ticketDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO createNew(final TicketDTO ticketDTO) {
        return saveAndReturnDTO(ticketMapper.ticketDTOToTicket(ticketDTO));
    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }

    @Override
    public TicketDTO saveAndReturnDTO(final Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);

        TicketDTO ticketDTO = ticketMapper.ticketToTicketDTO(savedTicket);
        ticketDTO.setUrl(URL + savedTicket.getId());

        return ticketDTO;
    }
}
