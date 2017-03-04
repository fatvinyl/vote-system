package ru.fatvinyl.votesystem.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fatvinyl.votesystem.model.Meal;
import ru.fatvinyl.votesystem.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * @author Anton Yolgin
 */
@Repository
@Transactional(readOnly = true)
public class MealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Override
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
        meal.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Meal.GET)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id) {
        List<Meal> meals = em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id", id)
                .getResultList();
        return DataAccessUtils.singleResult(meals);
    }

}
