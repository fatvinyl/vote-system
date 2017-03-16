package ru.fatvinyl.votesystem.util.exception;

/**
 * @author Anton Yolgin
 */

public class NotEqualException extends RuntimeException {
    public NotEqualException(String message) {
        super(message);
    }
}