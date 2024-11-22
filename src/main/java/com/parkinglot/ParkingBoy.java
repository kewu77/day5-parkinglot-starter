package com.parkinglot;

public class ParkingBoy {
    private ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park (Car car) throws ParkingException{
        if(parkingLot == null)
            return null;
        return parkingLot.park(car);
    }
}
