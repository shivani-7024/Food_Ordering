package shivani.food_odering.Request;

import lombok.Data;

@Data
public class IngredientRequest {
    private String name;
    private Long categoryId;
    private Long RestaurantId;
}
