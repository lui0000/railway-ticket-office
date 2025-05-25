package com.example.sale_of_railway_tickets.controllers;

import com.example.sale_of_railway_tickets.models.Train;
import com.example.sale_of_railway_tickets.services.TrainService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Train> create(@RequestBody @Validated Train train) {
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
}
