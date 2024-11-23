package com.parkinglot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ParkingLot {
    private int MAX_CAPACITY = 10;
    public static final String ERROR_TICKET_MESSAGE = "Unrecognized parking ticket";
    public static final String NO_CAPACITY_MESSAGE = "No available position";

    private Long id = System.currentTimeMillis() + new Random().nextLong();

    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    private Integer capacity = MAX_CAPACITY;

    public ParkingLot() {
    }

    public ParkingLot(Integer capacity) {
        MAX_CAPACITY = capacity;
        this.capacity = capacity;
    }

    public boolean checkParkingCapacity(){
        return capacity > 0;
    }

    public Ticket park(Car car) {
        if (checkParkingCapacity()) {
            Ticket ticket = new Ticket();
            ticket.setParkingLotID(id);
            ticketToCar.put(ticket, car);
            capacity --;
            return ticket;
        }else{
            throw new NoAvailablePositionException();
        }
    }

    public Car fetch(Ticket ticket) {
        Car resultCar = ticketToCar.remove(ticket);
        if(resultCar != null)
            capacity ++;
        else
            throw new UnrecognizedParkingTicketException();
        return resultCar;
    }

    public Long getId() {
        return id;
    }

    public int getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
