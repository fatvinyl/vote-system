package ru.fatvinyl.votesystem.web;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("user", userService.get(AuthorizedUser.id()));
//        model.addAttribute("restaurants", restaurantService.getAllWIthDishesAndVotes(LocalDate.now()));
//        model.addAttribute("voteId", 4);
        return "restaurants";
    }

}
