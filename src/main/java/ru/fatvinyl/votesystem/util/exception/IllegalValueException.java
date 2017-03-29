package ru.fatvinyl.votesystem.util.exception;

/**
 * @author Anton Yolgin
 */

public class IllegalValueException extends RuntimeException {
    public IllegalValueException(String message) {
        super(message);
    }
}
