package shivani.food_odering.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shivani.food_odering.Dto.RestaurentDto;
import shivani.food_odering.Model.Address;
import shivani.food_odering.Model.Restaurent;
import shivani.food_odering.Model.User;
import shivani.food_odering.Repository.AddressRepository;
import shivani.food_odering.Repository.RestaurantRepository;
import shivani.food_odering.Repository.UserRepository;
import shivani.food_odering.Request.CreateRestaurantRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestuarentServiceImp implements RestuarentService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurent createRestaurent(CreateRestaurantRequest createRestaurantRequest, User user) {

        Address address = addressRepository.save(createRestaurantRequest.getAddress());
        Restaurent restaurent = new Restaurent();

        restaurent.setAddress(address);
        restaurent.setContactInformation(createRestaurantRequest.getContactInformation());
        restaurent.setCuisineType(createRestaurantRequest.getCuisineType());
        restaurent.setDescription(createRestaurantRequest.getDescription());
        restaurent.setName(createRestaurantRequest.getName());
        restaurent.setImages(createRestaurantRequest.getImages());
        restaurent.setOpeningHours(createRestaurantRequest.getOpeningTime());
        restaurent.setRegiatrationDate(LocalDateTime.now());
        restaurent.setOwner(user);
        
        return restaurantRepository.save(restaurent);
    }

    @Override
    public Restaurent updateRestaurent(Long restuarentId, CreateRestaurantRequest updateRestaurantRequest) throws Exception {
        Restaurent restaurent = getRestaurentById(restuarentId);

        if(restaurent.getCuisineType()!=null) {
            restaurent.setCuisineType(updateRestaurantRequest.getCuisineType());
        }
        if(restaurent.getDescription()!=null){
            restaurent.setDescription(updateRestaurantRequest.getDescription());
        }
        if(restaurent.getName()!=null){
            restaurent.setName(updateRestaurantRequest.getName());
        }
        return restaurantRepository.save(restaurent);
    }

    @Override
    public void deleteRestaurent(Long restuarentId) throws Exception {
        Restaurent restaurent = getRestaurentById(restuarentId);

        restaurantRepository.delete(restaurent);

    }

    @Override
    public List<Restaurent> searchRestaurent(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public List<Restaurent> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurent getRestaurentById(Long restuarentId) throws Exception {
        Optional<Restaurent> opt = restaurantRepository.findById(restuarentId);

        if(opt.isEmpty()){
            throw new Exception("Restaurent not found with id "+restuarentId);
        }
        return opt.get();
    }

    @Override
    public Restaurent getRestaurentByUserId(Long userId) throws Exception {
        Restaurent restaurent = restaurantRepository.findByOwnerId(userId);
        if(restaurent==null){
            throw new Exception("Restaurent not found with id "+userId);
        }
        return restaurent;
    }

    @Override
    public RestaurentDto favoriteRestaurent(Long restuarentId, User user) throws Exception {

        Restaurent restaurent = getRestaurentById(restuarentId);
        RestaurentDto restaurentDto = new RestaurentDto();
        restaurentDto.setDescription(restaurent.getDescription());
        restaurentDto.setImages(restaurent.getImages());
        restaurentDto.setTitle(restaurent.getName());
        restaurentDto.setId(restuarentId);

        boolean isFav = false;
        List<RestaurentDto> fav = user.getFavorites();
        for (RestaurentDto favorite : fav){
            if(favorite.getId() == (restuarentId)){
                isFav = true;
                break;
            }
        }

        if(isFav){
            fav.removeIf(favorite->favorite.getId() == (restuarentId));
        }
        else {
            fav.add(restaurentDto);
        }

        userRepository.save(user);
        return restaurentDto;
    }

    @Override
    public Restaurent updateRestaurentStatus(Long id) throws Exception {

        Restaurent restaurent = getRestaurentById(id);
        restaurent.setOpen(!restaurent.isOpen());

        return restaurantRepository.save(restaurent);
    }
}
