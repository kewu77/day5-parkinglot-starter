package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int MAX_CAPACITY = 10;

    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    private Integer capacity = MAX_CAPACITY;

    public Ticket park(Car car) {
        if (capacity > 0) {
            Ticket ticket = new Ticket();
            ticketToCar.put(ticket, car);
            capacity --;
            return ticket;
        }else{
            return null;
        }
    }

    public Car fetch(Ticket ticket) {
        return ticketToCar.remove(ticket);
    }
}
