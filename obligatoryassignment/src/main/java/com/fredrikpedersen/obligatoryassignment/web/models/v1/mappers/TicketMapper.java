package com.fredrikpedersen.obligatoryassignment.web.models.v1.mappers;

import com.fredrikpedersen.obligatoryassignment.domain.models.Ticket;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapstruct mapper for converting Ticket objects to TicketDTOs and vice-versa
 *
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 21:10
 */

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket ticketDTOToTicket(final TicketDTO ticketDTO);

    @Mapping(target = "url", source = "")
    TicketDTO ticketToTicketDTO(final Ticket ticket);
}
