package ru.fatvinyl.votesystem.web.restaurant;

import org.apache.commons.io.FileUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.to.RestaurantWithVote;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Anton Yolgin
 */

@RestController
@RequestMapping(value = "/ajax/profile/restaurants")
public class RestaurantAjaxController extends AbstractRestaurantController {

    private Path path;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVote> getAllWIthDishesAndVotes() {
        return super.getAllWIthDishesAndVotes(LocalDate.now());
    }

    @PostMapping(value = "/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVote> getAllFiltered(
            @RequestParam("menuDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate menuDate) {
        return super.getAllWIthDishesAndVotes(menuDate);
    }

    @Override
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void createOrUpdate(@Valid  Restaurant restaurant, HttpServletRequest request) {

        if (restaurant.isNew()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, restaurant.getId());
        }

        MultipartFile restaurantImage = restaurant.getRestaurantImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "/resources/images/" + restaurant.getId() + ".png");

            try {
                if (restaurantImage != null && !restaurantImage.isEmpty()) {
                    restaurantImage.transferTo(new File(path.toString()));
                } else {
                    File source = new File(rootDirectory + "/resources/images/default_logo.png");
                    File dest = new File(rootDirectory + "/resources/images/" + restaurant.getId() + ".png");
                    FileUtils.copyFile(source, dest);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Product image saving failed", ex);
            }

    }

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id")int id, HttpServletRequest request) {

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "/WEB-INF/resources/images/" + id + ".png");
        if(Files.exists(path)){
            try {
                Files.delete(path);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        super.delete(id);
    }
}
