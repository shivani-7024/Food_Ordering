package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
