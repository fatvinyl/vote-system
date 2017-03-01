package ru.fatvinyl.votesystem.repository;

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
public class RestaurantRepositoryImpl implements RestaurantRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurant> getAll(LocalDate date) {

        return em.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class)
                .setParameter("date", date)
                .getResultList();
    }
}
