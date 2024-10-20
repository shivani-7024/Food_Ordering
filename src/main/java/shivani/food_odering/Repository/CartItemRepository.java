package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.Cart;
import shivani.food_odering.Model.CartItems;
import shivani.food_odering.Model.User;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
    public Cart findByCustomerId(Long userId);
    public Optional<CartItems> findById(Long cartItemId);
    List<CartItems> findByCustomer(User customer);
}
