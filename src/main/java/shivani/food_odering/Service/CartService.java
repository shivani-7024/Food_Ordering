package shivani.food_odering.Service;

import shivani.food_odering.Model.Cart;
import shivani.food_odering.Model.CartItems;
import shivani.food_odering.Request.AddCartItemRequest;

public interface CartService {
    public CartItems addToCart(AddCartItemRequest request, String token)throws Exception;

    public CartItems updateCartItemQuantity(Long cartItemId, int quantity)throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String token)throws Exception;

    public long calculateCartTotals(Cart cart)throws Exception;

    public Cart findCartById(Long id)throws Exception;

    public Cart findCartByUserId(Long userId)throws Exception;

    public Cart clearCart(Long userId)throws Exception;
}
