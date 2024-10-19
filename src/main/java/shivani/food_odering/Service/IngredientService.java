package shivani.food_odering.Service;


import shivani.food_odering.Model.IngredientsCategory;
import shivani.food_odering.Model.IngredientsItem;

import java.util.List;

public interface IngredientService {

    public IngredientsCategory createIngredientCategory(String name, Long RestaurantId)throws Exception;

    public IngredientsCategory findIngredientCategoryById(Long id)throws Exception;

    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id)throws Exception;

    public IngredientsItem createIngredientItems(Long restaurantId, String name, Long categoryId)throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId)throws Exception;

    public IngredientsItem updateStock(Long id)throws Exception;
}
