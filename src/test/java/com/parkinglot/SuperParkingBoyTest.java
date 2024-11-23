package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    @Test
    public void should_return_first_parking_lot_ticket_when_park_given_a_car_and_same_empty_positions_rate(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.manage(firstParkingLot);
        superParkingBoy.manage(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = superParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(firstParkingLot.getId(),ticket.getParkingLotID());
    }
}
