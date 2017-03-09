package ru.fatvinyl.votesystem.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fatvinyl.votesystem.model.User;
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
    public Vote create(int restaurantId, int userId) {
        Vote created = new Vote(1, restaurantId);
        em.persist(created);
        User userRef = em.getReference(User.class, userId);
        userRef.setVote(created);
        em.merge(userRef);
        return created;
    }

    @Override
    public boolean update(int voteId, int userId) {
        Vote voteRef = em.getReference(Vote.class, voteId);
        User userRef = em.getReference(User.class, userId);
        userRef.setVote(voteRef);
        em.merge(userRef);
        return  em.createNamedQuery(Vote.INCREMENT)
                .setParameter("voteId", voteId)
                .executeUpdate()!=0;
    }

    @Override
    public boolean delete(int voteId, int userId) {
        User userRef = em.getReference(User.class, userId);
        userRef.setVote(null);
        em.merge(userRef);
        return  em.createNamedQuery(Vote.DECREMENT)
                .setParameter("voteId", voteId)
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
