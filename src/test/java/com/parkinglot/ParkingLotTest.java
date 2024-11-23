package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingLotTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void should_return_ticket_when_park_given_a_car(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assertEquals(car,fetchedCar);
    }

    @Test
    public void should_return_two_car_when_park_given_two_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car SecondCar = new Car();
        Ticket firstTicket = parkingLot.park(firstCar);
        Ticket SecondTicket = parkingLot.park(SecondCar);
        //When
        Car fetchedFirstCar = parkingLot.fetch(firstTicket);
        Car fetchedSecondCar = parkingLot.fetch(SecondTicket);
        //Then
        assertEquals(firstCar,fetchedFirstCar);
        assertEquals(SecondCar,fetchedSecondCar);
    }


    @Test
    public void should_return_error_message_when_fetch_given_a_wrong_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class,() -> parkingLot.fetch(wrongTicket), ParkingLot.ERROR_TICKET_MESSAGE);

    }

    @Test
    public void should_return_error_message_when_fetch_given_a_used_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car SecondCar = new Car();
        Ticket firstTicket = parkingLot.park(firstCar);
        parkingLot.park(SecondCar);
        parkingLot.fetch(firstTicket);
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class,() -> parkingLot.fetch(firstTicket), ParkingLot.ERROR_TICKET_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_parking_lot_full_given_a_car(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 10; i++)
            parkingLot.park(new Car());
        Car lastCar = new Car();

        //When
        //Then
        assertThrows(NoAvailablePositionException.class,() -> parkingLot.park(lastCar), ParkingLot.NO_CAPACITY_MESSAGE);

    }

    private String systemOut() {
        return outContent.toString();
    }
}
