package com.project.bookmyshow.repositories;

import com.project.bookmyshow.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket save(Ticket ticket);
}
