package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByCustomerId(Long userId);

    public List<Order> findByRestaurantId(Long restaurantId);
}
