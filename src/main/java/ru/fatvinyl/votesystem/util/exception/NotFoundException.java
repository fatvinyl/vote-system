package ru.fatvinyl.votesystem.util.exception;

/**
 * @author Anton Yolgin
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
