package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class parkingBoyTest {

    @Test
    public void should_return_ticket_when_park_given_a_car_and_parking_lot(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        //When
        Car fetchedCar = parkingBoy.fetch(ticket);
        //Then
        assertEquals(car,fetchedCar);
    }

    @Test
    public void should_return_two_car_when_park_given_two_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car SecondCar = new Car();
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket SecondTicket = parkingBoy.park(SecondCar);
        //When
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket);
        Car fetchedSecondCar = parkingBoy.fetch(SecondTicket);
        //Then
        assertEquals(firstCar,fetchedFirstCar);
        assertEquals(SecondCar,fetchedSecondCar);
    }


    @Test
    public void should_return_error_message_when_fetch_given_a_wrong_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        //When
        //Then
        assertThrows(ParkingException.class,() -> parkingBoy.fetch(wrongTicket), ParkingLot.ERROR_TICKET_MESSAGE);

    }
}
