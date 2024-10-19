package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.Order;
import shivani.food_odering.Model.User;
import shivani.food_odering.Service.OrderService;
import shivani.food_odering.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long id,
                                             @RequestParam(required = false)String order_status,
                                             @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getRestaurantOrders(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId,
                                                       @PathVariable String orderStatus,
                                                       @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.updateOrder(orderId, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
