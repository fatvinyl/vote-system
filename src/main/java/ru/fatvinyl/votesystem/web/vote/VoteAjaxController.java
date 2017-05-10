package ru.fatvinyl.votesystem.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalTime;

import static ru.fatvinyl.votesystem.util.VoteUtil.decrementVote;
import static ru.fatvinyl.votesystem.util.VoteUtil.incrementVote;

/**
 * @author Anton Yolgin
 */


@RestController
@RequestMapping(value = "/ajax/profile/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAjaxController extends AbstractVoteController{

    @PostMapping(value = "/{restaurantId}")
    Vote create(@PathVariable("restaurantId") int restaurantId) {
        return super.create(restaurantId, LocalTime.now());
    }

    @PostMapping(value = "/increment/{voteId}&{restaurantId}")
    Vote increment(Vote vote, @PathVariable("voteId") int voteId, @PathVariable("restaurantId") int restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        vote.setRestaurant(restaurant);
        return super.increment(vote, voteId, LocalTime.now());
    }

    @PostMapping(value = "/decrement/{voteId}&{restaurantId}")
    Vote decrement(Vote vote, @PathVariable("voteId") int voteId, @PathVariable("restaurantId") int restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        vote.setRestaurant(restaurant);
        return super.decrement(vote, voteId, LocalTime.now());
    }

}
