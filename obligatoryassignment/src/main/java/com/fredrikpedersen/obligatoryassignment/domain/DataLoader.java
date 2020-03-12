package com.fredrikpedersen.obligatoryassignment.domain;

import com.fredrikpedersen.obligatoryassignment.domain.models.Ticket;
import com.fredrikpedersen.obligatoryassignment.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class for seeding data to the database on application start.
 * By implementing CommandLineRunner the class' run method is called once the Spring Context
 * is done loading.
 *
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 21:34
 */

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final TicketRepository ticketRepository;

    public DataLoader(final TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Seeding data to database...");
        seedTickets();
        log.info("Done seeding data!");
    }

    private void seedTickets() {
        log.info("Seeding tickets...");

        Ticket ticket1 = Ticket.builder()
                .firstName("Fredrik")
                .lastName("Pedersen")
                .email("mail@mail.com")
                .phoneNumber("12345678")
                .movie("Avengers: Secret Wars")
                .quantity(2)
                .build();

        Ticket ticket2 = Ticket.builder()
                .firstName("Signe")
                .lastName("Eide")
                .email("mail@mail.com")
                .phoneNumber("87654321")
                .movie("Black Widow")
                .quantity(5)
                .build();

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
    }
}
