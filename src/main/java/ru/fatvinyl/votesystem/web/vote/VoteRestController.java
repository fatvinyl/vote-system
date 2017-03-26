package ru.fatvinyl.votesystem.web.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fatvinyl.votesystem.model.Vote;

import java.net.URI;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/votes";


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vote> createWithLocation(@RequestBody int restaurantId) {
        Vote created = super.create(restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Vote update(@RequestBody Vote vote, @PathVariable("id") int id) {
        return super.update(vote, id);
    }

    @Override
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    Vote get(@PathVariable("id") int id) {
        return super.get(id);
    }

}
