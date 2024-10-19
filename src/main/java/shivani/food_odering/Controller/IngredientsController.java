package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivani.food_odering.Model.IngredientsCategory;
import shivani.food_odering.Model.IngredientsItem;
import shivani.food_odering.Request.IngredientCategoryRequest;
import shivani.food_odering.Request.IngredientRequest;
import shivani.food_odering.Service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest ingredientCategoryRequest) throws Exception {
        IngredientsCategory ingredientsCategory = ingredientService.createIngredientCategory(ingredientCategoryRequest.getName(),
                ingredientCategoryRequest.getRestaurantId());
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest ingredientRequest) throws Exception {

        IngredientsItem item = ingredientService.createIngredientItems(ingredientRequest.getRestaurantId(), ingredientRequest.getName(), ingredientRequest.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientItem(
            @PathVariable Long id) throws Exception {

        IngredientsItem item = ingredientService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id) throws Exception {
        List<IngredientsItem> item = ingredientService.findRestaurantIngredients(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id) throws Exception {
        List<IngredientsCategory> item = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
