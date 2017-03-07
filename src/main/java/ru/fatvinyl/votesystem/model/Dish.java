package ru.fatvinyl.votesystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * @author Anton Yolgin
 */

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Dish.GET, query = "SELECT m FROM Dish m WHERE m.id=:id"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish m WHERE m.id=:id")
})

@Entity
@Table(name = "meals")
public class Dish extends NamedEntity {

    public static final String GET = "Dish.getByMealDate";
    public static final String DELETE = "Dish.delete";

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

    public Dish() {
    }

    public Dish(Integer id, String name, String price, LocalDate date) {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + getId() +
                ", name=" + name +
                ", price='" + price +
                ", date=" + date +
//                ", restaurant=" + restaurant +
                "}";
    }
}
