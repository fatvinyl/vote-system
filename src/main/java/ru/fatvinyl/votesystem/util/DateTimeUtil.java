package ru.fatvinyl.votesystem.util;

import java.time.format.DateTimeFormatter;

/**
 * @author Anton Yolgin
 */

public class DateTimeUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

}
