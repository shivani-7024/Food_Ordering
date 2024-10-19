package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
