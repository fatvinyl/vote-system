package ru.fatvinyl.votesystem.util;

import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;
import ru.fatvinyl.votesystem.util.exception.NotEqualException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Anton Yolgin
 */

public class RestaurantUtil {

    public static List<RestaurantWithVote> getWithVote(List<Restaurant> restaurants, List<Vote> votes) {

        restaurants.stream()
                .map(restaurant -> createWithVote(restaurant, votes.get(restaurant.getId())))
                .collect(Collectors.toList());
        return null;
    }

    public static RestaurantWithVote createWithVote(Restaurant restaurant, Vote vote) throws NotEqualException {
        if (Objects.equals(restaurant.getId(), vote.getId())) {
            return new RestaurantWithVote(restaurant.getId(), restaurant.getName(), restaurant.getDishList(), vote.getAmount());
        }
        throw new NotEqualException("restaurant_id="+ restaurant.getId() + " and vote.getRestaurantId=" + vote.getRestaurant().getId() + " are not equal");
    }
}
