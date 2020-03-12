package com.fredrikpedersen.obligatoryassignment.repository;

import com.fredrikpedersen.obligatoryassignment.domain.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 20:02
 */

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
