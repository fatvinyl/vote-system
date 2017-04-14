package ru.fatvinyl.votesystem.to;


import com.fasterxml.jackson.annotation.JsonProperty;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.model.Vote;

import java.util.Collection;

/**
 * @author Anton Yolgin
 */

public class RestaurantWithVote extends BaseTo{

    @JsonProperty("restaurantName")
    private String name;

    private Collection<Dish> menu;

    private Vote vote;

    public RestaurantWithVote(@JsonProperty("id") Integer id,
                              @JsonProperty("name") String name,
                              @JsonProperty("menu") Collection<Dish> menu,
                              @JsonProperty("vote") Vote vote) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.vote = vote;
    }

    public RestaurantWithVote(Restaurant restaurant, Vote vote) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.menu = restaurant.getDishList();
        this.vote = vote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Dish> getMenu() {
        return menu;
    }

    public void setMenu(Collection<Dish> menu) {
        this.menu = menu;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "RestaurantWithVote{" +
                "id=" + id +
                ", name=" + name +
                ", menu=" + menu +
                ", vote=" + vote +
                '}';
    }

}
