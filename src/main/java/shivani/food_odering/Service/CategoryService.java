package shivani.food_odering.Service;

import shivani.food_odering.Model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;
    public List<Category> findCategoriesByRestaurantId(Long id) throws Exception;
    public Category findCategoryById(Long id) throws Exception;
}
