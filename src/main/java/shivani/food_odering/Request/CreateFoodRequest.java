package shivani.food_odering.Request;

import lombok.Data;
import shivani.food_odering.Model.Category;
import shivani.food_odering.Model.IngredientsItem;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;

    private List<String> images;
    private List<IngredientsItem> ingredients;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
}
