package ru.fatvinyl.votesystem.to;


import ru.fatvinyl.votesystem.model.Dish;

import java.util.List;

/**
 * @author Anton Yolgin
 */

public class RestaurantWithVote {

    private Integer id;

    private String name;

    private List<Dish> menu;

    private Integer amountVotes;

    public RestaurantWithVote(Integer id, String name, List<Dish> menu, Integer amountVotes) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.amountVotes = amountVotes;
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

    public Integer getAmountVotes() {
        return amountVotes;
    }

    public void setAmountVotes(Integer amountVotes) {
        this.amountVotes = amountVotes;
    }

    @Override
    public String toString() {
        return "RestaurantWithVote{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", amountVotes=" + amountVotes +
                '}';
    }
}
