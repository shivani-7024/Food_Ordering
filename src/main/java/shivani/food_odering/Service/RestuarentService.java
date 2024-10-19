package shivani.food_odering.Service;

import shivani.food_odering.Dto.RestaurentDto;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Model.User;
import shivani.food_odering.Request.CreateRestaurantRequest;

import java.util.List;

public interface RestuarentService {
    public Restaurent createRestaurent(CreateRestaurantRequest createRestaurantRequest, User user);

    public Restaurent updateRestaurent(Long restuarentId, CreateRestaurantRequest updateRestaurantRequest) throws Exception;

    public void deleteRestaurent(Long restuarentId) throws Exception;

    public List<Restaurent> searchRestaurent(String keyword) throws Exception;

    public List<Restaurent> getRestaurants();

    public Restaurent getRestaurentById(Long restuarentId)throws Exception;

    public Restaurent getRestaurentByUserId(Long userId)throws Exception;

    public RestaurentDto favoriteRestaurent(Long restuarentId, User user)throws Exception;

    public Restaurent updateRestaurentStatus(Long id) throws Exception;

}
