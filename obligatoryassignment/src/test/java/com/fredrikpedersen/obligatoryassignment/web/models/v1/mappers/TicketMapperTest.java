package com.fredrikpedersen.obligatoryassignment.web.models.v1.mappers;

import com.fredrikpedersen.obligatoryassignment.domain.models.Ticket;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 21:15
 */

public class TicketMapperTest {

    private TicketMapper ticketMapper = new TicketMapperImpl();

    private final Long ID = 1L;

    @Test
    void ticketToTicketDTOTest() {

        //given
        Ticket source = Ticket.builder()
                .id(ID)
                .build();

        //when
        TicketDTO target = ticketMapper.ticketToTicketDTO(source);

        //then
        assertEquals(source.getId(), target.getId());
    }

    @Test
    void ticketDTOToTicketTest() {

        //given
        TicketDTO source = TicketDTO.builder()
                .id(ID)
                .build();

        //when
        Ticket target = ticketMapper.ticketDTOToTicket(source);

        //then
        assertEquals(source.getId(), target.getId());
    }
}
