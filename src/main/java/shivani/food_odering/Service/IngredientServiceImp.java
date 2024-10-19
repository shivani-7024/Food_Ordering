package shivani.food_odering.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shivani.food_odering.Model.IngredientsCategory;
import shivani.food_odering.Model.IngredientsItem;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Repository.IngredientCategoryRepository;
import shivani.food_odering.Repository.IngredientItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestuarentService restuarentService;

    @Override
    public IngredientsCategory createIngredientCategory(String name, Long RestaurantId) throws Exception {
        Restaurent restaurent = restuarentService.getRestaurentById(RestaurantId);
        IngredientsCategory ingredientCategory = new IngredientsCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurent);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientsCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> ingredientCategory = ingredientCategoryRepository.findById(id);
        if(ingredientCategory.isEmpty()){
            throw new Exception("Ingredient category not found");
        }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restuarentService.getRestaurentById(id);
        return ingredientCategoryRepository.findRestaurantById(id);
    }

    @Override
    public IngredientsItem createIngredientItems(Long restaurantId, String name, Long categoryId) throws Exception {
        Restaurent restaurent = restuarentService.getRestaurentById(restaurantId);
        IngredientsCategory ingredientCategory = findIngredientCategoryById(categoryId);
        IngredientsItem ingredientItem = new IngredientsItem();
        ingredientItem.setName(name);
        ingredientItem.setRestaurent(restaurent);
        ingredientItem.setCategory(ingredientCategory);

        IngredientsItem ingredients = ingredientItemRepository.save(ingredientItem);
        ingredientCategory.getItems().add(ingredients);

        return ingredients.getCategory();
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> ingredientItem = ingredientItemRepository.findById(id);
        if(ingredientItem.isEmpty()){
            throw new Exception("Ingredient item not found");
        }
        IngredientsItem ingredients = ingredientItem.get();
        ingredients.setInStock(!ingredients.isInStock());
        return ingredientItemRepository.save(ingredients);
    }
}
