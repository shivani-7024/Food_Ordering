package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.IngredientsCategory;

import java.util.List;


public interface IngredientCategoryRepository extends JpaRepository<IngredientsCategory, Long> {

    List<IngredientsCategory> findRestaurantById(Long id);
}
