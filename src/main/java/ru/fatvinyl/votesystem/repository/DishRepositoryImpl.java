package ru.fatvinyl.votesystem.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Anton Yolgin
 */
@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Override
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        dish.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.GET)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Dish get(int id) {
        List<Dish> dishes = em.createNamedQuery(Dish.GET, Dish.class)
                .setParameter("id", id)
                .getResultList();
        return DataAccessUtils.singleResult(dishes);
    }

}
