package ru.fatvinyl.votesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_BY_DATE, query = "SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishList m WHERE m.date=:date ORDER BY r.name"),
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET, query = "SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishList m WHERE r.id=:id AND m.date=:mealdate")
})

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    public static final String ALL_BY_DATE = "Restaurant.getAllByDate";
    public static final String ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET = "Restaurant.getByMealDate";


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name")
    private List<Dish> dishList;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Vote vote;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, Dish... dishes) {
        this.id = id;
        this.name = name;
        this.dishList = Arrays.asList(dishes);
    }
    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.dishList = new ArrayList<>();
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", dishList=" + dishList +
                "} ";
    }
}
