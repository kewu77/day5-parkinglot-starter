package com.parkinglot;

import java.util.List;
import java.util.Optional;

public class NoCleverParkStrategy implements ParkStrategy{
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) throws NoAvailablePositionException{
        Optional<ParkingLot> availableParkingLot = parkingLots.stream().filter(ParkingLot::checkParkingCapacity).findFirst();
        if(availableParkingLot.isPresent())
            return availableParkingLot.get().park(car);
        else
            throw new NoAvailablePositionException();
    }
}
