package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
