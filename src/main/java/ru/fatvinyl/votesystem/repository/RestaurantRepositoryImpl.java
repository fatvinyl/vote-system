package ru.fatvinyl.votesystem.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fatvinyl.votesystem.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Anton Yolgin
 */

@Repository
@Transactional(readOnly = true)
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurant> getAllWIthVotesAndDishes(LocalDate date) {

        return em.createNamedQuery(Restaurant.ALL_WITH_VOTES_AND_DISHES, Restaurant.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class)
                .getResultList();
    }


    @Override
    public Restaurant getByMealDate(int id, LocalDate date) {
        List<Restaurant> restaurants = em.createNamedQuery(Restaurant.GET, Restaurant.class)
                .setParameter("id", id)
                .setParameter("date", date)
                .getResultList();
        return DataAccessUtils.singleResult(restaurants);
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }
}
