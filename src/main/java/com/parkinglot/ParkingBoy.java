package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();

    protected ParkStrategy parkStrategy;

    public ParkingBoy(ParkStrategy parkStrategy) {
        this.parkStrategy = parkStrategy;
    }

    public ParkingBoy(ParkingLot parkingLot, ParkStrategy parkStrategy) {
        this.parkingLots.add(parkingLot);
        this.parkStrategy = parkStrategy;
    }

    public Ticket park (Car car) throws NoAvailablePositionException{
        if(parkingLots.size() == 0 || parkStrategy == null)
            return null;
        return parkStrategy.park(parkingLots,car);
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException{
        if(parkingLots.size() == 0)
            return null;
        Optional<ParkingLot> availableParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.getId().equals(ticket.getParkingLotID())).findFirst();
        if(availableParkingLot.isPresent()) {
            return availableParkingLot.get().fetch(ticket);
        }
        else {
            throw new UnrecognizedParkingTicketException();
        }
    }

    public void manage(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
}
