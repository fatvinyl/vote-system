package ru.fatvinyl.votesystem.util;

import org.springframework.util.Assert;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.model.Vote;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Anton Yolgin
 */

public class RestaurantUtil {


    /**
     * @param restaurants List<Restaurant> must be sorted by restaurant_id
     * @param votes       List<Vote> must be sorted by restaurant_id
     */

    public static List<RestaurantWithVote> getWithVote(List<Restaurant> restaurants, List<Vote> votes) {
        Assert.notNull(restaurants, "restaurants must not be null");
        if (votes == null || votes.isEmpty()) {
            return createWithoutVotes(restaurants);
        } else if (restaurants.size() > votes.size()) {
            return createWithNullVotes(restaurants, votes);
        } else return createWithVotes(restaurants, votes);
    }

    /**
     * @param restaurants List<Restaurant> must be sorted by restaurant_id
     */
    public static List<RestaurantWithVote> createWithoutVotes(List<Restaurant> restaurants) {
        List<RestaurantWithVote> restaurantWithVotes = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantWithVotes.add(new RestaurantWithVote(restaurant, null));
        }
        return restaurantWithVotes.stream()
                .sorted(Comparator.comparing(RestaurantWithVote::getName))
                .collect(Collectors.toList());
    }

    /**
     * @param restaurants List<Restaurant> must be sorted by restaurant_id
     * @param votes       List<Vote> must be sorted by restaurant_id
     */
    public static List<RestaurantWithVote> createWithVotes(List<Restaurant> restaurants, List<Vote> votes) {
        List<RestaurantWithVote> restaurantWithVotes = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            restaurantWithVotes.add(i, new RestaurantWithVote(restaurants.get(i), votes.get(i)));
        }
        return restaurantWithVotes.stream()
                .sorted(Comparator.comparing(RestaurantWithVote::getName))
                .collect(Collectors.toList());
    }

    /**
     * @param restaurants List<Restaurant> must be sorted by restaurant_id
     * @param votes       List<Vote> must be sorted by restaurant_id
     */
    public static List<RestaurantWithVote> createWithNullVotes(List<Restaurant> restaurants, List<Vote> votes) {
        List<RestaurantWithVote> restaurantWithVotes = new ArrayList<>();
        int matches = 0;
        for (int i = 0; i < restaurants.size(); i++) {
            if ( matches < votes.size() && votes.get(matches).getRestaurant().getId().equals(restaurants.get(i).getId())) {
                restaurantWithVotes.add(i, new RestaurantWithVote(restaurants.get(i), votes.get(matches)));
                matches++;
            } else {
                restaurantWithVotes.add(i, new RestaurantWithVote(restaurants.get(i), null));
            }
        }
        return restaurantWithVotes.stream()
                .sorted(Comparator.comparing(RestaurantWithVote::getName))
                .collect(Collectors.toList());
    }


}
