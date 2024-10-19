package shivani.food_odering.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shivani.food_odering.Model.Category;
import shivani.food_odering.Model.Food;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Repository.FoodRepository;
import shivani.food_odering.Request.CreateFoodRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class FoodServiceImp implements FoodService {

  @Autowired
  private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurent restaurant) {

        Food food = new Food();
        food.setCategory(category);
        food.setRestaurent(restaurant);
        food.setRestaurent(restaurant);
        food.setDescription(request.getDescription());
        food.setPrice(request.getPrice());
        food.setImages(request.getImages());
        food.setName(request.getName());
        food.setPrice(request.getPrice());
        food.setIngredients(request.getIngredients());
        food.setSeasonal(request.isSeasonal());
        food.setVegetarian(request.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurent(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantFoods(Long restaurantId, boolean isVegitarain,
                                         boolean isNonVeg, boolean isSeasonal,
                                         String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurentId(restaurantId);

        if(isVegitarain){
            foods = filterByVegetarian(foods, isVegitarain);
        }
        if(isNonVeg){
            foods = filterByNonVeg(foods, isNonVeg);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory!=null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }
        return foods;
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter((food-> food.isVegetarian()==false)).collect(Collectors.toList());

    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getCategory()!=null){
                return food.getCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter((food) -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarain) {
        return foods.stream().filter((food-> food.isVegetarian()==true)).collect(Collectors.toList());

    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long id) throws Exception {

        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()){
            throw new Exception("Food not exist...");
        }
        return food.get();
    }

    @Override
    public Food updateAvailibilityStatus(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
