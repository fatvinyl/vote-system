package ru.fatvinyl.votesystem.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.fatvinyl.votesystem.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Anton Yolgin
 */

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurant> getAllWIthDishes(LocalDate date) {

        List<Restaurant> list = em.createNamedQuery(Restaurant.ALL_WITH_VOTES_AND_DISHES, Restaurant.class)
                .setParameter("date", date)
                .getResultList();
        return list;
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class)
                .getResultList();
    }

    @Override
    public Restaurant getByDate(int id, LocalDate date) {
        List<Restaurant> restaurants = em.createNamedQuery(Restaurant.GET, Restaurant.class)
                .setParameter("id", id)
                .setParameter("date", date)
                .getResultList();
        return DataAccessUtils.singleResult(restaurants);
    }


    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }
}
