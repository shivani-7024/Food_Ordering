package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shivani.food_odering.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
