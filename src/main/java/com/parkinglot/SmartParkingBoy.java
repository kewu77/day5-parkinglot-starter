package com.parkinglot;

import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy{
    @Override
    public Ticket park (Car car) throws NoAvailablePositionException{
        if(parkingLots.size() == 0)
            return null;
        Optional<ParkingLot> availableParkingLot = parkingLots.stream().
                filter(ParkingLot::checkParkingCapacity)
                .min((parkingLot, nextParkingLot) -> nextParkingLot.getCapacity() - parkingLot.getCapacity());
        if(availableParkingLot.isPresent())
            return availableParkingLot.get().park(car);
        else
            throw new NoAvailablePositionException();
    }
}
