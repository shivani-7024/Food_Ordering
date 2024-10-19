package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Dto.RestaurentDto;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Model.User;
import shivani.food_odering.Service.RestuarentService;
import shivani.food_odering.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private RestuarentService restuarentService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurent>> searchRestaurant(@RequestHeader("Authorization")String jwt ,
                                                             @RequestParam String keyword) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Restaurent> restaurent = restuarentService.searchRestaurent(keyword);
        return new ResponseEntity<>(restaurent, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Restaurent>> getAllRestaurant(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Restaurent> restaurent = restuarentService.getRestaurants();
        return new ResponseEntity<>(restaurent, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurent> findRestaurantById(@RequestHeader("Authorization")String jwt ,
                                                             @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restuarentService.getRestaurentById(id);
        return new ResponseEntity<>(restaurent, HttpStatus.OK);

    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurentDto> addToFavorite(@RequestHeader("Authorization")String jwt ,
                                                         @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        RestaurentDto restaurent = restuarentService.favoriteRestaurent(id, user);
        return new ResponseEntity<>(restaurent, HttpStatus.OK);

    }

}
