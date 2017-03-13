package ru.fatvinyl.votesystem.util;

import ru.fatvinyl.votesystem.model.BaseEntity;
import ru.fatvinyl.votesystem.util.exception.NotFoundException;
import ru.fatvinyl.votesystem.util.exception.VotingTimeOverException;

import java.time.LocalTime;

/**
 *
 */

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void checkIdConsistent(BaseEntity entity, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    public static void checkVotingTime() {
        LocalTime current = LocalTime.now();
        LocalTime deadLine = LocalTime.parse("11:00");
        if (current.isAfter(deadLine)) {
            throw new VotingTimeOverException("The voting time was over");
        }
    }

}
