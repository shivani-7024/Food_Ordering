package shivani.food_odering.Request;

import lombok.Data;
import shivani.food_odering.Model.Address;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
