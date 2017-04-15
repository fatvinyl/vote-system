package ru.fatvinyl.votesystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.fatvinyl.votesystem.AuthorizedUser;
import ru.fatvinyl.votesystem.service.RestaurantService;
import ru.fatvinyl.votesystem.service.UserService;

import java.time.LocalDate;

/**
 * @author Anton Yolgin
 */

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String restaurants() {
        return "restaurants";
    }

}
