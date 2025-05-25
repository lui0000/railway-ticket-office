package com.example.sale_of_railway_tickets.controllers;

import com.example.sale_of_railway_tickets.models.Station;
import com.example.sale_of_railway_tickets.services.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
@Validated
public class StationController {
    private final StationService service;

    public StationController(StationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Station> create(@RequestBody @Validated Station station) {
        Station saved = service.create(station);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<Station> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Station getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/city/{city}")
    public List<Station> getByCity(@PathVariable String city) {
        return service.findByCity(city);
    }

    @PutMapping("/{id}")
    public Station update(@PathVariable String id, @RequestBody @Validated Station station) {
        return service.update(id, station);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}