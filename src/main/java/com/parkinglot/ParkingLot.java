package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int MAX_CAPACITY = 10;
    public static final String ERROR_TICKET_MESSAGE = "Unrecognized parking ticket";

    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    private Integer capacity = MAX_CAPACITY;

    public boolean checkParkingCapacity(){
        return capacity > 0;
    }

    public Ticket park(Car car) {
        if (checkParkingCapacity()) {
            Ticket ticket = new Ticket();
            ticketToCar.put(ticket, car);
            capacity --;
            return ticket;
        }else{
            return null;
        }
    }

    public Car fetch(Ticket ticket) {
        Car resultCar = ticketToCar.remove(ticket);
        if(resultCar != null)
            capacity ++;
        else
            throw new ParkingException(ERROR_TICKET_MESSAGE);
        return resultCar;
    }
}
