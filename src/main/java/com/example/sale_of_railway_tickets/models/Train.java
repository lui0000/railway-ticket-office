package com.example.sale_of_railway_tickets.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@JsonIgnoreProperties()
@Document(collection = "trains")
public class Train {

    @Id
    private String id;


    @NotBlank(message = "Train number is required")
    private String number;


    @NotBlank(message = "Train name is required")
    private String name;


    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;

    public Train() {
    }

    public Train(String id, String number, String name, int capacity) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}