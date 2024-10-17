package com.itschool.performance_tracker.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Player not found with Id: ";

    public PlayerNotFoundException(Long Id) {
        super(DEFAULT_MESSAGE + Id);
    }
}
