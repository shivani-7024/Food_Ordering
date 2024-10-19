package shivani.food_odering.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shivani.food_odering.Config.JwtProvider;
import shivani.food_odering.Model.User;
import shivani.food_odering.Repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByJwtToken(String jwtToken) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwtToken);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }
}
