package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shivani.food_odering.Model.Food;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurentId(long restaurantId);

    @Query("SELECT f from Food f where f.name LIKE %:keyword% or f.category.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword")String keyword);
}
