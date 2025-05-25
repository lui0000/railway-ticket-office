package com.example.sale_of_railway_tickets.repositories;

import com.example.sale_of_railway_tickets.models.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByTrainId(String trainId);
}
