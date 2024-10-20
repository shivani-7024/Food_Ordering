package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.Order;
import shivani.food_odering.Model.Restaurent;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long userId);

    List<Order> findByRestaurent(Restaurent restaurent);
}
