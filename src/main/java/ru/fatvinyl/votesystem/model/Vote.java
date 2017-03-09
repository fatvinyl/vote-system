package ru.fatvinyl.votesystem.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Anton Yolgin
 */

@NamedQueries({
        @NamedQuery(name = Vote.INCREMENT, query = "UPDATE Vote v SET v.amount=v.amount+1 " +
                "WHERE v.id=:voteId"),
        @NamedQuery(name = Vote.DECREMENT, query = "UPDATE Vote v SET v.amount=v.amount-1"+
                "WHERE v.id=:voteId"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id=:id")
})


@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    public static final String INCREMENT = "Vote.increment";
    public static final String DECREMENT = "Vote.decrement";
    public static final String GET = "Vote.get";

    @Column(name = "amount")
    @NotNull
    private Integer amount;

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Integer amount, Integer restaurantId) {
        this(null, amount, LocalDate.now(), restaurantId);
    }

    //The constructor is used to create test objects
    public Vote(Integer id, Integer amount, LocalDate date) {
        this(id, amount, date, null);
    }

    public Vote(Integer id, Integer amount, LocalDate date, Integer restaurantId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.restaurant = new Restaurant();
        this.restaurant.setId(restaurantId);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
