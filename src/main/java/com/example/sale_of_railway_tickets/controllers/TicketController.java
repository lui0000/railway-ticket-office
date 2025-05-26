package com.example.sale_of_railway_tickets.controllers;

import com.example.sale_of_railway_tickets.models.Ticket;
import com.example.sale_of_railway_tickets.services.TicketService;
import com.example.sale_of_railway_tickets.util.errorresponse.StationErrorResponse;
import com.example.sale_of_railway_tickets.util.errorresponse.TicketErrorResponse;
import com.example.sale_of_railway_tickets.util.notcreatedexception.StationNotCreatedException;
import com.example.sale_of_railway_tickets.util.notcreatedexception.TicketNotCreatedException;
import com.example.sale_of_railway_tickets.util.notfoundexception.StationNotFoundException;
import com.example.sale_of_railway_tickets.util.notfoundexception.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public ResponseEntity<Ticket> book(@RequestBody Ticket ticket, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new TicketNotCreatedException(errorMsg.toString());
        }
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

    @ExceptionHandler
    private ResponseEntity<TicketErrorResponse> handleException(TicketNotFoundException e) {
        TicketErrorResponse response = new TicketErrorResponse(
                "Ticket with this id wasn't found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<TicketErrorResponse> handleException(TicketNotCreatedException e) {
        TicketErrorResponse response = new TicketErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}