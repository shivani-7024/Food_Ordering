package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.Cart;
import shivani.food_odering.Model.CartItems;
import shivani.food_odering.Model.User;
import shivani.food_odering.Request.AddCartItemRequest;
import shivani.food_odering.Request.UpdateCartItemRequest;
import shivani.food_odering.Service.CartService;
import shivani.food_odering.Service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItems> addItemToCart(@RequestBody AddCartItemRequest reg,
                                                   @RequestHeader("Authorization")String jwt) throws Exception{
        CartItems cartItems = cartService.addToCart(reg, jwt);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItems> UpdateCartItemQuantity(@RequestBody UpdateCartItemRequest reg,
                                                   @RequestHeader("Authorization")String jwt) throws Exception{
        CartItems cartItems = cartService.updateCartItemQuantity(reg.getCartItemId(), reg.getQuantity());
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PutMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                               @RequestHeader("Authorization")String jwt) throws Exception{
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
                                               @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
