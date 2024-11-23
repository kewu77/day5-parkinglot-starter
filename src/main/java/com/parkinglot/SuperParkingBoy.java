package com.parkinglot;

import java.util.Optional;

public class SuperParkingBoy extends ParkingBoy{
    @Override
    public Ticket park (Car car) throws NoAvailablePositionException{
        if(parkingLots.size() == 0)
            return null;
        Optional<ParkingLot> availableParkingLot = parkingLots.stream().
                filter(ParkingLot::checkParkingCapacity)
                .min((parkingLot, nextParkingLot) ->
                        nextParkingLot.getParkingSpaceUnderUtilizationRate() - parkingLot.getParkingSpaceUnderUtilizationRate() > 0 ? 1 : 0);
        if(availableParkingLot.isPresent())
            return availableParkingLot.get().park(car);
        else
            throw new NoAvailablePositionException();
    }
}
