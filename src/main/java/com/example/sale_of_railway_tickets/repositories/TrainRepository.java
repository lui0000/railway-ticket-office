package com.example.sale_of_railway_tickets.repositories;

import com.example.sale_of_railway_tickets.models.Train;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TrainRepository extends MongoRepository<Train, String> {
    Optional<Train> findByNumber(String number);
}