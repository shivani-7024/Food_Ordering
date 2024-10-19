package shivani.food_odering.Request;

import lombok.Data;
import shivani.food_odering.Model.Address;
import shivani.food_odering.Model.ContactInformation;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingTime;
    private List<String> images;
}
