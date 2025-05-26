package com.example.sale_of_railway_tickets.util.notcreatedexception;

public class TicketNotCreatedException extends RuntimeException{
    public TicketNotCreatedException(String msg) {
        super(msg);
    }
}
