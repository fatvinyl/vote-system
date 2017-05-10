package ru.fatvinyl.votesystem.web.vote;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fatvinyl.votesystem.model.Vote;

import java.net.URI;
import java.time.LocalTime;

import static ru.fatvinyl.votesystem.util.VoteUtil.decrementVote;
import static ru.fatvinyl.votesystem.util.VoteUtil.incrementVote;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/votes";


    @PostMapping(value = "/{restaurantId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vote> createWithLocation(@PathVariable("restaurantId") int restaurantId) {
        Vote created = super.create(restaurantId, LocalTime.now());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/increment/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Vote increment(@RequestBody Vote vote, @PathVariable("id") int id) {
        return super.increment(vote, id, LocalTime.now());
    }

    @PutMapping(value = "/decrement/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Vote decrement(@RequestBody Vote vote, @PathVariable("id") int id) {
        return super.decrement(vote, id, LocalTime.now());
    }

}
