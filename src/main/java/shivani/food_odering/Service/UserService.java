package shivani.food_odering.Service;

import shivani.food_odering.Model.User;

public interface UserService {

    public User findUserByJwtToken(String jwtToken) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
