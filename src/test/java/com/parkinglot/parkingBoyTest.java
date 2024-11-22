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
    public void should_return_the_car_when_fetch_given_a_ticket_and_parking_lot(){
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
    public void should_return_two_car_when_park_given_two_ticket_and_parking_lot(){
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
    public void should_return_error_message_when_fetch_given_a_wrong_ticket_and_parking_lot(){
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

    @Test
    public void should_return_error_message_when_fetch_given_a_used_ticket_and_parking_lot(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car SecondCar = new Car();
        Ticket firstTicket = parkingBoy.park(firstCar);
        parkingBoy.park(SecondCar);
        parkingBoy.fetch(firstTicket);
        //When
        //Then
        assertThrows(ParkingException.class,() -> parkingBoy.fetch(firstTicket), ParkingLot.ERROR_TICKET_MESSAGE);
    }

    @Test
    public void should_return_error_message_when_parking_lot_full_given_a_car_and_parking_lot(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int i = 0; i < ParkingLot.MAX_CAPACITY; i++)
            parkingBoy.park(new Car());
        Car lastCar = new Car();

        //When
        //Then
        assertThrows(ParkingException.class,() -> parkingBoy.park(lastCar), ParkingLot.NO_CAPACITY_MESSAGE);

    }

    @Test
    public void should_return_first_parking_lot_ticket_when_park_given_a_car_and_two_no_full_parking_lot(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(firstParkingLot);
        parkingBoy.manage(secondParkingLot);
        for (int i = 0; i < ParkingLot.MAX_CAPACITY; i++)
            parkingBoy.park(new Car());
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(secondParkingLot.getId(),ticket.getParkingLotID());
    }

    @Test
    public void should_return_second_parking_lot_ticket_when_park_given_a_car_and_two_parking_lot(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(firstParkingLot);
        parkingBoy.manage(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(firstParkingLot.getId(),ticket.getParkingLotID());
    }



}
