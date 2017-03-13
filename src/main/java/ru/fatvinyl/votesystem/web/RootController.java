package ru.fatvinyl.votesystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.fatvinyl.votesystem.service.RestaurantService;

import java.time.LocalDate;

/**
 * @author Anton Yolgin
 */

@Controller
public class RootController {

    @Autowired
    private RestaurantService service;

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("restaurants", service.getAllWIthVotesAndDishes(LocalDate.now()));
        return "restaurants";
    }

}
