package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import java.time.LocalDate;

import static java.time.LocalDate.of;

/**
 * @author Anton Yolgin
 */

public class DishTestData {

    public final static LocalDate TEST_DATE = LocalDate.parse("2017-01-11", DateTimeUtil.DATE_FORMATTER);
    public static final ModelMatcher<Dish> DISHES_MATCHER = ModelMatcher.of(Dish.class);

    public static final Dish DISH_1 = new Dish(9, "Тестовая еда 1", "100,00", TEST_DATE);
    public static final Dish DISH_2 = new Dish(10, "Тестовая еда 2", "150,00", TEST_DATE);
    public static final Dish DISH_3 = new Dish(11, "Тестовая еда 3", "50,00", TEST_DATE);
    public static final Dish DISH_4 = new Dish(12, "Тестовая еда 4", "70,00", TEST_DATE);
    public static final Dish DISH_5 = new Dish(13, "Тестовая еда 5", "120,00", TEST_DATE);
    public static final Dish DISH_6 = new Dish(14, "Тестовая еда 6", "30,00", TEST_DATE);
    public static final Dish DISH_7 = new Dish(15, "Тестовая еда 7", "30,00", TEST_DATE);
    public static final Dish DISH_8 = new Dish(16, "Тестовая еда 8", "30,00", TEST_DATE);


}
