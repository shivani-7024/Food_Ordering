package shivani.food_odering.Request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
    private String name;
    private Long restaurantId;
}
