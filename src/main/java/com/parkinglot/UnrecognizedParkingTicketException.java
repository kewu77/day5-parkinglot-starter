package com.parkinglot;

public class UnrecognizedParkingTicketException extends RuntimeException{
    public static final String ERROR_TICKET_MESSAGE = "Unrecognized parking ticket";
    public UnrecognizedParkingTicketException() {
        super(ERROR_TICKET_MESSAGE);
    }
}
