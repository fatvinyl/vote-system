package ru.fatvinyl.votesystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @author Anton Yolgin
 */


@NamedQueries({
//        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
})

@Entity
@Table(name = "meals")
public class Meal extends NamedEntity {

    public static final String ALL_SORTED = "Meal.getAll";

    @Column(name = "price", nullable = false)
    @NotBlank
//    @SafeHtml
    private String price;

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(Integer id, String name, String price, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant_id() {
        return restaurant;
    }

    public void setRestaurant_id(Restaurant restaurant_id) {
        this.restaurant = restaurant_id;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + getId() +
                ", name=" + name +
                ", price='" + price +
                ", date=" + date +
//                ", restaurant=" + restaurant +
                "}";
    }
}
