package com.example.sale_of_railway_tickets.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Builder
@JsonIgnoreProperties()
@Document(collection = "tickets")
public class Ticket {
    @Id
    private String id;
    private String trainId;
    private String passengerName;
    private LocalDateTime departureTime;
    private String fromStation;
    private String toStation;
    private double price;

    public Ticket() {
    }

    public Ticket(String id, String trainId, String passengerName, LocalDateTime departureTime, String fromStation, String toStation, double price) {
        this.id = id;
        this.trainId = trainId;
        this.passengerName = passengerName;
        this.departureTime = departureTime;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}