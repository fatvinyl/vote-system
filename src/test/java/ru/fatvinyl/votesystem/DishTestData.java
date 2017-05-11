package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;

/**
 * @author Anton Yolgin
 */

public class DishTestData {

    public final static LocalDate TEST_DATE = LocalDate.parse("2017-01-11", DateTimeUtil.DATE_FORMATTER);
    public static final ModelMatcher<Dish> DISHES_MATCHER = ModelMatcher.of(Dish.class);


    public static final int DISH1_ID = 23;
    public static final int DISH6_ID = 28;
    public static final int CREATED_ID = 29;

    public static final Dish DISH_1 = new Dish(23, "Тестовая еда 1", "100-00", TEST_DATE);
    public static final Dish DISH_2 = new Dish(24, "Тестовая еда 2", "150-00", TEST_DATE);
    public static final Dish DISH_3 = new Dish(25, "Тестовая еда 3", "50-00", TEST_DATE);
    public static final Dish DISH_4 = new Dish(26, "Тестовая еда 4", "70-00", TEST_DATE);
    public static final Dish DISH_5 = new Dish(27, "Тестовая еда 5", "120-00", TEST_DATE);
    public static final Dish DISH_6 = new Dish(28, "Тестовая еда 6", "30-00", TEST_DATE);

    public static final List<Dish> EXCEPTED_DISHES = Arrays.asList(DISH_1, DISH_2);

    public static Dish getCreated() {
        return new Dish(null, "Созданное блюдо", "100-00", TEST_DATE);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Обновленное блюдо", "100-00", TEST_DATE);
    }

}
