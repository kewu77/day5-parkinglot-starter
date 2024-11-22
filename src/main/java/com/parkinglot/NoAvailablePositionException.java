package com.parkinglot;

public class NoAvailablePositionException extends RuntimeException{
    public static final String NO_CAPACITY_MESSAGE = "No available position";
    public NoAvailablePositionException() {
        super(NO_CAPACITY_MESSAGE);
    }
}
