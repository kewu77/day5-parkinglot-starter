package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    public void should_return_first_parking_lot_ticket_when_park_given_a_car_and_same_empty_positions(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new ParkingBoy(new SmartParkStrategy());
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
        ParkingBoy smartParkingBoy = new ParkingBoy(new SmartParkStrategy());
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
    public void should_return_two_Corresponding_cars_when_park_given_two_ticket_and_two_parking_lot(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new ParkingBoy(new SmartParkStrategy());
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
        ParkingBoy smartParkingBoy = new ParkingBoy(new SmartParkStrategy());
        smartParkingBoy.manage(firstParkingLot);
        smartParkingBoy.manage(secondParkingLot);
        Car car = new Car();
        smartParkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class,() -> smartParkingBoy.fetch(wrongTicket), UnrecognizedParkingTicketException.ERROR_TICKET_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_fetch_given_a_used_ticket_and_two_parking_lot(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new ParkingBoy(new SmartParkStrategy());
        smartParkingBoy.manage(firstParkingLot);
        smartParkingBoy.manage(secondParkingLot);
        Car firstCar = new Car();
        Car SecondCar = new Car();
        Ticket firstTicket = smartParkingBoy.park(firstCar);
        smartParkingBoy.park(SecondCar);
        smartParkingBoy.fetch(firstTicket);
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class,() -> smartParkingBoy.fetch(firstTicket), UnrecognizedParkingTicketException.ERROR_TICKET_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_parking_lot_full_given_a_car_and_two_parking_lot(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new ParkingBoy(new SmartParkStrategy());
        smartParkingBoy.manage(firstParkingLot);
        smartParkingBoy.manage(secondParkingLot);
        for (int i = 0; i <  10 * 2; i++)
            smartParkingBoy.park(new Car());
        Car lastCar = new Car();
        //When
        //Then
        assertThrows(NoAvailablePositionException.class,() -> smartParkingBoy.park(lastCar), NoAvailablePositionException.NO_CAPACITY_MESSAGE);

    }
}
