package com.example.sale_of_railway_tickets.services;

import com.example.sale_of_railway_tickets.models.Train;
import com.example.sale_of_railway_tickets.repositories.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private final TrainRepository repo;

    public TrainService(TrainRepository repo) {
        this.repo = repo;
    }

    public Train create(Train train) {
        return repo.save(train);
    }

    public List<Train> findAll() {
        return repo.findAll();
    }

    public Train findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found: " + id));
    }

    public Train update(String id, Train updated) {
        Train existing = findById(id);
        existing.setNumber(updated.getNumber());
        existing.setName(updated.getName());
        existing.setCapacity(updated.getCapacity());
        return repo.save(existing);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}