package shivani.food_odering.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shivani.food_odering.Model.Category;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private RestuarentService restuarentService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurent restaurent = restuarentService.getRestaurentByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurent);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoriesByRestaurantId(Long id) throws Exception {
        Restaurent restaurent = restuarentService.getRestaurentByUserId(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("Category not found");
        }
        return category.get();
    }
}
