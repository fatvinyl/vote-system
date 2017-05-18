package ru.fatvinyl.votesystem.repository;

import org.springframework.stereotype.Repository;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */
@Repository
public class DishDAOImpl implements DishDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Dish get(int id) {
        return em.find(Dish.class, id);
    }

    @Override
    public List<Dish> getAllByDate(LocalDate date, int restaurantId) {
        return em.createNamedQuery(Dish.GET_ALL_BY_DATE, Dish.class)
                .setParameter("date", date)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

}
