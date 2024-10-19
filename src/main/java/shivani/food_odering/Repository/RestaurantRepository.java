package shivani.food_odering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shivani.food_odering.Model.Restaurent;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurent, Long> {

    @Query("select r from Restaurent r where lower(r.name) LIKE lower(concat('%',:query,'%')) or lower(r.cuisineType) LIKE lower(concat('%',:query,'%'))")
    List<Restaurent> findBySearchQuery(@Param("query") String query);
    Restaurent findByOwnerId(Long id);
}
