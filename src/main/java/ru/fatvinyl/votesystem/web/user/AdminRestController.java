package ru.fatvinyl.votesystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fatvinyl.votesystem.model.User;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserController {
    static final String REST_URL = "/rest/admin/users";


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody User user) {
        User created = super.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);

    }@Override
    void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    User getByEmail(@RequestParam("email") String email) {
        return super.getByEmail(email);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> getAll() {
        return super.getAll();
    }
}
