package shivani.food_odering.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shivani.food_odering.Config.JwtProvider;
import shivani.food_odering.Model.Cart;
import shivani.food_odering.Model.USER_ROLE;
import shivani.food_odering.Model.User;
import shivani.food_odering.Repository.CartRepository;
import shivani.food_odering.Repository.UserRepository;
import shivani.food_odering.Request.LoginRequest;
import shivani.food_odering.Response.AuthResponse;
import shivani.food_odering.Service.CustomerUserDetailsServices;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailsServices customerUserDetailsServices;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExists = userRepository.findByEmail(user.getEmail());
        if (isEmailExists != null) {
            throw new Exception("Email is already used in another account");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setName(user.getName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setRole(user.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request) throws Exception {
        String email = request.getEmail();
        String password = request.getPassword();
        Authentication authentication = authenticate(email, password);

        Collection<?extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully");
        authResponse.setRole(USER_ROLE.valueOf(role));


        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customerUserDetailsServices.loadUserByUsername(email);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}