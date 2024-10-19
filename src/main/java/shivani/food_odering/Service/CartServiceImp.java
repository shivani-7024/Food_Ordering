package shivani.food_odering.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shivani.food_odering.Model.Cart;
import shivani.food_odering.Model.CartItems;
import shivani.food_odering.Model.Food;
import shivani.food_odering.Model.User;
import shivani.food_odering.Repository.CartItemRepository;
import shivani.food_odering.Repository.CartRepository;
import shivani.food_odering.Request.AddCartItemRequest;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;
    @Override
    public CartItems addToCart(AddCartItemRequest request, String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Food food = foodService.findFoodById(request.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItems cartItem : cart.getCartItems()){
            if(cartItem.getFood().getId() == food.getId()){
                int newQuantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItems newCartItem = new CartItems();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setPrice(request.getQuantity()*food.getPrice());

        CartItems savedCartItem = new CartItems();
        cart.getCartItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItems> cartOptional = cartItemRepository.findById(cartItemId);
        if(cartOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItems cartItem = cartOptional.get();
        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getFood().getPrice()*quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItems> cartOptional = cartItemRepository.findById(cartItemId);
        if(cartOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItems cartItem = cartOptional.get();
        cart.getCartItems().remove(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public long calculateCartTotals(Cart cart) throws Exception {

        Long Total = 0L;
        for(CartItems cartItem : cart.getCartItems()){
            Total += cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return Total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartOptional.isEmpty()){
            throw new Exception("Cart not found with Id : "+id);
        }
        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
