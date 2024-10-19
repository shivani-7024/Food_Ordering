package shivani.food_odering.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @ManyToOne
    private IngredientsCategory category;

    @JsonIgnore
    @ManyToOne
    private Restaurent restaurent;

    private boolean inStock = true;
}
