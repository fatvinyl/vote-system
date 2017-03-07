package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Restaurant;


import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static ru.fatvinyl.votesystem.DishTestData.*;

/**
 * @author Anton Yolgin
 */

public class RestaurantTestData {


    public static final ModelMatcher<Restaurant> RESTAURANT_MATCHER = ModelMatcher.of(Restaurant.class);

    public static final int RESTAURANT1_ID = 0;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Шашлыкофф", 0, DISH_1, DISH_2);
    public static final Restaurant RESTAURANT2 = new Restaurant(1, "Макдоналдс", 0, DISH_3, DISH_4);
    public static final Restaurant RESTAURANT3 = new Restaurant(2, "Лаундж", 0, DISH_5, DISH_6);

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT3, RESTAURANT2, RESTAURANT1);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный ресторан", 0, DISH_1, DISH_2);
    }

}
