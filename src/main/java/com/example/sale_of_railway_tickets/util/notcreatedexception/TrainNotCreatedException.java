package com.example.sale_of_railway_tickets.util.notcreatedexception;

public class TrainNotCreatedException extends RuntimeException{
    public TrainNotCreatedException(String msg) {
        super(msg);
    }
}
