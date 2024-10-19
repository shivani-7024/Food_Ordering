package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.Food;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Model.User;
import shivani.food_odering.Repository.UserRepository;
import shivani.food_odering.Request.CreateFoodRequest;
import shivani.food_odering.Response.MessageResponse;
import shivani.food_odering.Service.FoodService;
import shivani.food_odering.Service.RestuarentService;
import shivani.food_odering.Service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestuarentService restuarentService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest createFoodRequest,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurent restaurent = restuarentService.getRestaurentById(createFoodRequest.getRestaurantId());
        Food food = foodService.createFood(createFoodRequest, createFoodRequest.getCategory(), restaurent);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Food Deleted Successfully");
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> UpdateFoodAvaibility(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailibilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
