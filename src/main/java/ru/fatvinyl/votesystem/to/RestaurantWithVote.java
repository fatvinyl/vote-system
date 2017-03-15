package ru.fatvinyl.votesystem.to;


import ru.fatvinyl.votesystem.model.Dish;

import java.util.List;

/**
 * @author Anton Yolgin
 */

public class RestaurantWithVote {

    private Integer id;

    private String name;

    private List<Dish> dishList;

    private Integer amountVotes;

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

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public Integer getAmountVotes() {
        return amountVotes;
    }

    public void setAmountVotes(Integer amountVotes) {
        this.amountVotes = amountVotes;
    }
}
