package com.example.sale_of_railway_tickets.services;

import com.example.sale_of_railway_tickets.models.Station;
import com.example.sale_of_railway_tickets.repositories.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private final StationRepository repo;

    public StationService(StationRepository repo) {
        this.repo = repo;
    }

    public Station create(Station station) {
        return repo.save(station);
    }

    public List<Station> findAll() {
        return repo.findAll();
    }

    public Station findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found: " + id));
    }

    public List<Station> findByCity(String city) {
        return repo.findByCity(city);
    }

    public Station update(String id, Station updated) {
        Station existing = findById(id);
        existing.setName(updated.getName());
        existing.setCity(updated.getCity());
        existing.setAddress(updated.getAddress());
        return repo.save(existing);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}