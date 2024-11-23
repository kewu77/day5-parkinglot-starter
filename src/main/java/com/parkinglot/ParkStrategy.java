package com.parkinglot;

import java.util.List;

public interface ParkStrategy {
    public Ticket park(List<ParkingLot> parkingLots, Car car) throws NoAvailablePositionException;
}
