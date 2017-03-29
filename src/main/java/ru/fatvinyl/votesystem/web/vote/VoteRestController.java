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


    @PostMapping(value = "/{restaurantId}&{currentTime}",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vote> createWithLocation(@PathVariable("restaurantId") int restaurantId,
                                            @PathVariable("currentTime")  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime currentTime) {
        Vote created = super.create(restaurantId, currentTime);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


//    @Override
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    Vote update(@RequestBody Vote vote, @PathVariable("id") int id) {
//        return super.update(vote, id);
//    }


    @PutMapping(value = "/increment/{id}&{currentTime}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Vote increment(@RequestBody Vote vote, @PathVariable("id") int id,
                   @PathVariable("currentTime")  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime currentTime) {
        return super.update(incrementVote(vote), id, currentTime);
    }

    @PutMapping(value = "/decrement/{id}&{currentTime}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Vote decrement(@RequestBody Vote vote, @PathVariable("id") int id,
                   @PathVariable("currentTime")  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime currentTime) {
        return super.update(decrementVote(vote), id, currentTime);
    }

//    @Override
//    @DeleteMapping("/{id}")
//    void delete(@PathVariable("id") int id) {
//        super.delete(id);
//    }

    @Override
    @GetMapping("/{id}")
    Vote get(@PathVariable("id") int id) {
        return super.get(id);
    }

}
