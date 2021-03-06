package ru.fatvinyl.votesystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
        @NamedQuery(name = Restaurant.ALL_WITH_DISHES, query = "SELECT DISTINCT r FROM Restaurant r " +
                "JOIN FETCH r.dishList d WHERE d.date=:date ORDER BY r.id"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET, query = "SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishList m WHERE r.id=:id AND m.date=:date")
})

@Entity
@Table(name = "restaurants")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Restaurant extends NamedEntity {
    public static final String ALL_WITH_DISHES = "Restaurant.getAllWIthDishes";
    public static final String ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET = "Restaurant.getByDate";


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name")
    @JsonIgnoreProperties("restaurant")
    private List<Dish> dishList;

    @Transient
    private MultipartFile restaurantImage;

    public Restaurant() {
    }

    //The constructor is used to create test objects
    public Restaurant(Integer id, String name, Dish... dishes) {
        this.id = id;
        this.name = name;
        this.dishList = Arrays.asList(dishes);
    }
    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }


    public MultipartFile getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(MultipartFile restaurantImage) {
        this.restaurantImage = restaurantImage;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name=" + getName() +
                "} ";
    }
}
