package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Model.User;
import shivani.food_odering.Request.CreateRestaurantRequest;
import shivani.food_odering.Response.MessageResponse;
import shivani.food_odering.Service.RestuarentService;
import shivani.food_odering.Service.UserService;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestuarentService restuarentService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Restaurent> createRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest,
                                                       @RequestHeader("Authorization")String jwt ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restuarentService.createRestaurent(createRestaurantRequest, user);
        return new ResponseEntity<>(restaurent, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurent> updateRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest,
                                                       @RequestHeader("Authorization")String jwt , @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restuarentService.updateRestaurent(id, createRestaurantRequest);
        return new ResponseEntity<>(restaurent, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization")String jwt , @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        restuarentService.deleteRestaurent(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurent> updateRestaurantStatus(@RequestHeader("Authorization")String jwt , @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restuarentService.updateRestaurentStatus(id);
        return new ResponseEntity<>(restaurent, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<Restaurent> findRestaurantByUserId(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restuarentService.getRestaurentByUserId(user.getId());
        return new ResponseEntity<>(restaurent, HttpStatus.OK);

    }
}
