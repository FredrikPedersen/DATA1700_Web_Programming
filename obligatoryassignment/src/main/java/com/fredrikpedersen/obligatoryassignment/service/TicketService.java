package com.fredrikpedersen.obligatoryassignment.service;

import com.fredrikpedersen.obligatoryassignment.domain.models.Ticket;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketDTO;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 20:20
 */

public interface TicketService extends CrudService<TicketDTO, Ticket, Long> {
}
