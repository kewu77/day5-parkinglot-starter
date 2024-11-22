package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    public ParkingBoy() {
    }

    public ParkingBoy(ParkingLot parkingLot) {
        if(parkingLot != null)
            this.parkingLots.add(parkingLot);
    }

    public Ticket park (Car car) throws ParkingException{
        if(parkingLots.size() == 0)
            return null;
        Optional<ParkingLot> availableParkingLot = parkingLots.stream().filter(ParkingLot::checkParkingCapacity).findFirst();
        if(availableParkingLot.isPresent())
            return availableParkingLot.get().park(car);
        else
            throw new ParkingException(ParkingLot.NO_CAPACITY_MESSAGE);
    }

    public Car fetch(Ticket ticket) throws ParkingException{
        if(parkingLots.size() == 0)
            return null;
        Optional<ParkingLot> availableParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.getId().equals(ticket.getParkingLotID())).findFirst();
        if(availableParkingLot.isPresent()) {
            return availableParkingLot.get().fetch(ticket);
        }
        else {
            throw new ParkingException(ParkingLot.ERROR_TICKET_MESSAGE);
        }

    }

    public void manage(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
}
