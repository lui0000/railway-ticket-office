package com.example.sale_of_railway_tickets.services;

import com.example.sale_of_railway_tickets.models.Ticket;
import com.example.sale_of_railway_tickets.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository repo;

    public TicketService(TicketRepository repo) {
        this.repo = repo;
    }

    public Ticket bookTicket(Ticket ticket) {
        return repo.save(ticket);
    }

    public List<Ticket> getTicketsByTrain(String trainId) {
        return repo.findByTrainId(trainId);
    }

    public void cancelTicket(String ticketId) {
        repo.deleteById(ticketId);
    }
}