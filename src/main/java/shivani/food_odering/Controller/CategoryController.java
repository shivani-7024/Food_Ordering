package shivani.food_odering.Controller;

import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.Category;
import shivani.food_odering.Model.User;
import shivani.food_odering.Service.CategoryService;
import shivani.food_odering.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization")String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Category createCategory = categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization")String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        List<Category> Categories = categoryService.findCategoriesByRestaurantId(user.getId());
        return new ResponseEntity<>(Categories, HttpStatus.CREATED);
    }
}
