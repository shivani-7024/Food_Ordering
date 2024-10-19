package shivani.food_odering.Service;

import shivani.food_odering.Model.Category;
import shivani.food_odering.Model.Food;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Request.CreateFoodRequest;

import java.util.List;

public interface FoodService{
    public Food createFood(CreateFoodRequest request, Category category, Restaurent restaurant);
    public void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantFoods(Long restaurantId, boolean isVegitarain,
                                         boolean isNonVeg, boolean isSeasonal, String foodCategory);

    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long id) throws Exception;
    public Food updateAvailibilityStatus(Long foodId) throws Exception;
}
