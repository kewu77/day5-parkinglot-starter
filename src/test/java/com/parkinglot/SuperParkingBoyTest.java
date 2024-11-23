package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    @Test
    public void should_return_first_parking_lot_ticket_when_park_given_a_car_and_same_empty_positions_rate(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(100);
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

    @Test
    public void should_return_second_parking_lot_ticket_when_park_given_a_car_and_second_has_higher_empty_positions_rate(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(100);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.manage(firstParkingLot);
        superParkingBoy.manage(secondParkingLot);
        //When
        superParkingBoy.park(new Car());
        Ticket secondTicket = superParkingBoy.park(new Car());
        Ticket thirdTicket = superParkingBoy.park(new Car());
        //Then
        assertNotNull(secondTicket);
        assertEquals(secondParkingLot.getId(),secondTicket.getParkingLotID());
        assertNotNull(thirdTicket);
        assertEquals(secondParkingLot.getId(),thirdTicket.getParkingLotID());
    }

    @Test
    public void should_return_two_Corresponding_cars_when_park_given_two_ticket_and_two_parking_lot(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(100);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        superParkingBoy.manage(firstParkingLot);
        superParkingBoy.manage(secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = superParkingBoy.park(firstCar);
        Ticket secondTicket = superParkingBoy.park(secondCar);
        //When
        Car fetchedFirstCar = superParkingBoy.fetch(firstTicket);
        Car fetchedSecondCar = superParkingBoy.fetch(secondTicket);
        //Then
        assertEquals(firstCar,fetchedFirstCar);
        assertEquals(secondCar,fetchedSecondCar);
    }
}
