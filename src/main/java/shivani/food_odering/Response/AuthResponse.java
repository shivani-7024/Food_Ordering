package shivani.food_odering.Response;

import lombok.Data;
import shivani.food_odering.Model.USER_ROLE;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
