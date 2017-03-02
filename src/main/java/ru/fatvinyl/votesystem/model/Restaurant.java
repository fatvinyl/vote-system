package ru.fatvinyl.votesystem.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Anton Yolgin
 */

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.mealList m WHERE m.date=:date ORDER BY r.name")

})

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    public static final String ALL_SORTED = "Restaurant.getAll";

    @Column(name = "amount_votes", nullable = false)
    @NotNull
    private Integer amountVotes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @OrderBy("name")
    private List<Meal> mealList;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, Integer amountVotes, Meal...meals) {
        this.id = id;
        this.name = name;
        this.amountVotes = amountVotes;
        this.mealList = Arrays.asList(meals);
    }

    public Integer getAmountVotes() {
        return amountVotes;
    }

    public void setAmountVotes(Integer amount_votes) {
        this.amountVotes = amount_votes;
    }

    public List<Meal> getmealList() {
        return mealList;
    }

    public void setmealList(List<Meal> mealList) {
        this.mealList = mealList;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name=" + name +
                ", amount_votes=" + amountVotes +
                ", mealList=" + mealList  +
                "} ";
    }
}
