package shivani.food_odering.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shivani.food_odering.Model.USER_ROLE;
import shivani.food_odering.Model.User;
import shivani.food_odering.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found with Email "+username);
        }

        USER_ROLE role = user.getRole();
        if(role==null){
            role = USER_ROLE.ROLE_CUSTOMER;
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
