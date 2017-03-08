package ru.fatvinyl.votesystem.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fatvinyl.votesystem.model.Vote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@Repository
@Transactional
public class VoteRepositoryImpl implements VoteRepository{

   @PersistenceContext
    public EntityManager em;

    @Override
    public Vote create(int restaurantId) {
        Vote created = new Vote(1, restaurantId);
        em.persist(created);
       return created;
    }


    @Override
    public boolean increment(LocalDate date, int restaurantId) {
        return  em.createNamedQuery(Vote.INCREMENT)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .executeUpdate()!=0;
    }

    @Override
    public boolean decrement(LocalDate date, int restaurantId) {
        return  em.createNamedQuery(Vote.DECREMENT)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .executeUpdate()!=0;
    }

    @Override
    public Vote get(int id) {
        List<Vote> vote = em.createNamedQuery(Vote.GET, Vote.class)
                .setParameter("id", id)
                .getResultList();
        return DataAccessUtils.singleResult(vote);
    }

}
