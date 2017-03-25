package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;


import java.util.Arrays;
import java.util.List;

import static ru.fatvinyl.votesystem.DishTestData.*;
import static ru.fatvinyl.votesystem.VoteTestData.VOTE1;
import static ru.fatvinyl.votesystem.VoteTestData.VOTE2;
import static ru.fatvinyl.votesystem.VoteTestData.VOTE3;

/**
 * @author Anton Yolgin
 */

public class RestaurantTestData {


    public static final ModelMatcher<Restaurant> RESTAURANT_MATCHER = ModelMatcher.of(Restaurant.class);

    public static final ModelMatcher<RestaurantWithVote> RESTAURANT_WITH_VOTE_MATCHER = ModelMatcher.of(RestaurantWithVote.class);

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int RESTAURANT_CREATED_ID = 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Шашлыкофф",  DISH_1, DISH_2);
    public static final Restaurant RESTAURANT2 = new Restaurant(2, "Макдоналдс", DISH_3, DISH_4);
    public static final Restaurant RESTAURANT3 = new Restaurant(3, "Лаундж", DISH_5, DISH_6);

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный ресторан");
    }

}
