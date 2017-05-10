package ru.fatvinyl.votesystem.repository;

import org.springframework.stereotype.Repository;

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
public class VoteDAOImpl implements VoteDAO {

   @PersistenceContext
    public EntityManager em;

      @Override
    public Vote save(Vote vote, int userId) {
        User userRef = em.getReference(User.class, userId);
        if (vote.isNew()) {
            vote.setAmount(1);
            em.persist(vote);
            userRef.setVote(vote);
            em.merge(userRef);
            return vote;
        } else {
            userRef.setVote(vote);
            em.merge(userRef);
            return em.merge(vote);
        }
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return em.createNamedQuery(Vote.ALL_BY_DATE, Vote.class)
                .setParameter("date", date)
                .getResultList();
    }


}
