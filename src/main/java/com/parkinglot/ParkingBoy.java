package com.parkinglot;

public class ParkingBoy {
    private ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        if(parkingLot != null)
           return parkingLot.park(car);
        else
            return null;
    }
}
