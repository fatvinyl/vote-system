package ru.fatvinyl.votesystem.to;


import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.model.Vote;

import java.util.List;

/**
 * @author Anton Yolgin
 */

public class RestaurantWithVote {

    private Integer id;

    private String name;

    private List<Dish> menu;

    private Vote vote;

    public RestaurantWithVote(Integer id, String name, List<Dish> menu, Vote vote) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.vote = vote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Integer amountVotes) {
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
