package ru.fatvinyl.votesystem.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Anton Yolgin
 */

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r WHERE r.user.id=:userId ORDER BY m.dateTime DESC")
})

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    public static final String ALL_SORTED = "Restaurant.getAll";

    @Column(name = "amount_votes", nullable = false)
    @NotNull
    private Integer amount_votes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Meal> mealSet;

    public Restaurant() {
    }

    public Integer getAmount_votes() {
        return amount_votes;
    }

    public void setAmount_votes(Integer amount_votes) {
        this.amount_votes = amount_votes;
    }

    public Set<Meal> getMealSet() {
        return mealSet;
    }

    public void setMealSet(Set<Meal> mealSet) {
        this.mealSet = mealSet;
    }
}
