package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Meal;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import java.time.LocalDate;

import static java.time.LocalDate.of;

/**
 * @author Anton Yolgin
 */

public class MealTestData {

    public final static LocalDate TEST_DATE = LocalDate.parse("2017-01-11", DateTimeUtil.DATE_FORMATTER);
    public static final ModelMatcher<Meal> MEALS_MATCHER = ModelMatcher.of(Meal.class);

    public static final Meal MEAL1 = new Meal(9, "Тестовая еда 1", "100,00", TEST_DATE);
    public static final Meal MEAL2 = new Meal(10, "Тестовая еда 2", "150,00", TEST_DATE);
    public static final Meal MEAL3 = new Meal(11, "Тестовая еда 3", "50,00", TEST_DATE);
    public static final Meal MEAL4 = new Meal(12, "Тестовая еда 4", "70,00", TEST_DATE);
    public static final Meal MEAL5 = new Meal(13, "Тестовая еда 5", "120,00", TEST_DATE);
    public static final Meal MEAL6 = new Meal(14, "Тестовая еда 6", "30,00", TEST_DATE);
    public static final Meal MEAL7 = new Meal(15, "Тестовая еда 7", "30,00", TEST_DATE);
    public static final Meal MEAL8 = new Meal(16, "Тестовая еда 8", "30,00", TEST_DATE);


}
