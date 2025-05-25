package com.example.sale_of_railway_tickets.controllers;

import com.example.sale_of_railway_tickets.models.Ticket;
import com.example.sale_of_railway_tickets.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;
    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public ResponseEntity<Ticket> book(@RequestBody Ticket ticket) {
        Ticket saved = service.bookTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/train/{trainId}")
    public List<Ticket> getByTrain(@PathVariable String trainId) {
        return service.getTicketsByTrain(trainId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable String id) {
        service.cancelTicket(id);
        return ResponseEntity.noContent().build();
    }
}