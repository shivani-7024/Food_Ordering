package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.Food;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Model.User;
import shivani.food_odering.Request.CreateFoodRequest;
import shivani.food_odering.Service.FoodService;
import shivani.food_odering.Service.RestuarentService;
import shivani.food_odering.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestuarentService restuarentService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.searchFood(name);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getrestaurantFood(@RequestParam boolean vegetarian,
                                                 @RequestParam boolean seasonal,
                                                 @RequestParam boolean nonVeg,
                                                 @RequestParam(required = false) String food_category,
                                                 @PathVariable Long restaurantId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.getRestaurantFoods(restaurantId, seasonal, vegetarian, nonVeg, food_category);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
