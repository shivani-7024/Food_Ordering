package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.IngredientsItem;
import shivani.food_odering.Model.Restaurent;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {

    List<IngredientsItem> findByRestaurantId(Long id);
    List<IngredientsItem> findByRestaurant(Restaurent restaurant);

}
