package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Restaurant;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDate.of;
import static ru.fatvinyl.votesystem.MealTestData.*;

/**
 * @author Anton Yolgin
 */

public class RestaurantTestData {

    public static final ModelMatcher<Restaurant> RESTAURANT_MATCHER = ModelMatcher.of(Restaurant.class);

    public static final Restaurant RESTAURANT1 = new Restaurant(0, "Шашлыкофф", 0, MEAL1, MEAL2);
    public static final Restaurant RESTAURANT2 = new Restaurant(1, "Макдоналдс", 0, MEAL3, MEAL4);
    public static final Restaurant RESTAURANT3 = new Restaurant(2, "Лаундж", 0, MEAL5, MEAL6);

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT3, RESTAURANT2, RESTAURANT1);

}
