package com.example.sale_of_railway_tickets.repositories;


import com.example.sale_of_railway_tickets.models.Station;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StationRepository extends MongoRepository<Station, String> {
    List<Station> findByCity(String city);
}
