package com.example.sale_of_railway_tickets.controllers;

import com.example.sale_of_railway_tickets.models.Train;
import com.example.sale_of_railway_tickets.services.TrainService;
import com.example.sale_of_railway_tickets.util.errorresponse.TicketErrorResponse;
import com.example.sale_of_railway_tickets.util.errorresponse.TrainErrorResponse;
import com.example.sale_of_railway_tickets.util.notcreatedexception.TicketNotCreatedException;
import com.example.sale_of_railway_tickets.util.notcreatedexception.TrainNotCreatedException;
import com.example.sale_of_railway_tickets.util.notfoundexception.TicketNotFoundException;
import com.example.sale_of_railway_tickets.util.notfoundexception.TrainNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
@Validated
public class TrainController {
    private final TrainService service;

    public TrainController(TrainService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Train> create(@RequestBody @Validated Train train, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new TrainNotCreatedException(errorMsg.toString());
        }
        Train saved = service.create(train);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<Train> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Train getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Train update(@PathVariable String id, @RequestBody @Validated Train train) {
        return service.update(id, train);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    private ResponseEntity<TrainErrorResponse> handleException(TrainNotFoundException e) {
        TrainErrorResponse response = new TrainErrorResponse(
                "Train with this id wasn't found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<TrainErrorResponse> handleException(TrainNotCreatedException e) {
        TrainErrorResponse response = new TrainErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
