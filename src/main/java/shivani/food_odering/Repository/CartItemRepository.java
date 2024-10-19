package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.Cart;
import shivani.food_odering.Model.CartItems;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
    public Cart findByCustomerId(Long userId);
    public Optional<CartItems> findById(Long cartItemId);
}
