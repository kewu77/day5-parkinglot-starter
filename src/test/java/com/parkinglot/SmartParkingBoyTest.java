package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    public void should_return_first_parking_lot_ticket_when_park_given_a_car_and_same_empty_positions(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(firstParkingLot);
        smartParkingBoy.manage(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = smartParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(firstParkingLot.getId(),ticket.getParkingLotID());
    }

    @Test
    public void should_return_second_parking_lot_ticket_when_park_given_a_car_and_second_has_more_empty_positions(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(firstParkingLot);
        smartParkingBoy.manage(secondParkingLot);
        //When
        Ticket firstTicket = smartParkingBoy.park(new Car());
        Ticket secondTicket = smartParkingBoy.park(new Car());
        //Then
        assertNotNull(firstTicket);
        assertEquals(firstParkingLot.getId(),firstTicket.getParkingLotID());
        assertNotNull(secondTicket);
        assertEquals(secondParkingLot.getId(),secondTicket.getParkingLotID());
    }

    @Test
    public void should_return_two_Corresponding_cars_when_park_given_two_ticket_and_first_full_second_no_full(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(firstParkingLot);
        smartParkingBoy.manage(secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = smartParkingBoy.park(firstCar);
        Ticket secondTicket = smartParkingBoy.park(secondCar);
        //When
        Car fetchedFirstCar = smartParkingBoy.fetch(firstTicket);
        Car fetchedSecondCar = smartParkingBoy.fetch(secondTicket);
        //Then
        assertEquals(firstCar,fetchedFirstCar);
        assertEquals(secondCar,fetchedSecondCar);
    }

    @Test
    public void should_return_error_message_when_fetch_given_a_wrong_ticket_and_two_parking_lot(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(firstParkingLot);
        smartParkingBoy.manage(secondParkingLot);
        Car car = new Car();
        smartParkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class,() -> smartParkingBoy.fetch(wrongTicket), UnrecognizedParkingTicketException.ERROR_TICKET_MESSAGE);
    }
}
