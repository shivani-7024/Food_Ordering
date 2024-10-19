package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.CartItems;
import shivani.food_odering.Model.Order;
import shivani.food_odering.Model.User;
import shivani.food_odering.Request.AddCartItemRequest;
import shivani.food_odering.Request.OrderRequest;
import shivani.food_odering.Service.OrderService;
import shivani.food_odering.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> CreateOrder(@RequestBody OrderRequest reg,
                                                   @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(reg, user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                                       @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
