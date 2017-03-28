package ru.fatvinyl.votesystem.util;

import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;
import ru.fatvinyl.votesystem.util.exception.NotEqualException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Anton Yolgin
 */

public class RestaurantUtil {

    public static List<RestaurantWithVote> getWithVote(List<Restaurant> restaurants, List<Vote> votes) {
        List<RestaurantWithVote> restaurantWithVotes = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            restaurantWithVotes.add(createWithVote(restaurants.get(i), votes.get(i)));
        }
        return restaurantWithVotes.stream()
                .sorted(Comparator.comparing(RestaurantWithVote::getName))
                .collect(Collectors.toList());
    }

    public static RestaurantWithVote createWithVote(Restaurant restaurant, Vote vote) throws NotEqualException {
        if (restaurant.getId().equals(vote.getRestaurant().getId())) {
            return new RestaurantWithVote(restaurant.getId(), restaurant.getName(), restaurant.getDishList(), vote);
        }
        throw new NotEqualException("restaurant_id=" + restaurant.getId() + " and vote.getRestaurantId=" + vote.getRestaurant().getId() + " are not equal");
    }
//    public static RestaurantWithVote createWithVote(Restaurant restaurant, Vote vote) throws NotEqualException {
//        if (restaurant.getId().equals(vote.getRestaurant().getId())) {
//            return new RestaurantWithVote(restaurant.getId(), restaurant.getName(), restaurant.getDishList(), vote.getId(), vote.getAmount());
//        }
//        throw new NotEqualException("restaurant_id=" + restaurant.getId() + " and vote.getRestaurantId=" + vote.getRestaurant().getId() + " are not equal");
//    }
}
