package com.fredrikpedersen.obligatoryassignment.service;

import com.fredrikpedersen.obligatoryassignment.domain.models.Ticket;
import com.fredrikpedersen.obligatoryassignment.repository.TicketRepository;
import com.fredrikpedersen.obligatoryassignment.web.controllers.v1.TicketController;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketDTO;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.mappers.TicketMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 22:34
 */
class TicketServiceImplTest {

    private final String URL = TicketController.BASE_URL;
    private final Long ID = 1L;
    private final String FIRST_NAME = "Fredrik";

    @Mock
    private TicketRepository ticketRepository;

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ticketService = new TicketServiceImpl(new TicketMapperImpl(), ticketRepository);
    }

    @Test
    void findAll() {

        //given
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket(), new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);

        //when
        List<TicketDTO> ticketDTOs = ticketService.findAll();

        //then
        assertEquals(tickets.size(), ticketDTOs.size());
    }

    @Test
    void createNew() {

        //given
        TicketDTO ticketDTO = TicketDTO.builder()
                .firstName(FIRST_NAME)
                .build();

        Ticket savedTicket = Ticket.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .build();

        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);

        //when
        TicketDTO savedDTO = ticketService.createNew(ticketDTO);

        //then
        assertEquals(ticketDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(URL + ID, savedDTO.getUrl());
    }

    @Test
    void deleteAll() {
        ticketRepository.deleteAll();
        verify(ticketRepository, times(1)).deleteAll();
    }
}