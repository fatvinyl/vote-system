package ru.fatvinyl.votesystem.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fatvinyl.votesystem.model.Meal;

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

    @Override
    public Meal save(Meal meal, int restaurantId) {
        return null;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return false;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return null;
    }

    @Override
    public Collection<Meal> getByDate(LocalDate date) {
        return null;
    }
}
