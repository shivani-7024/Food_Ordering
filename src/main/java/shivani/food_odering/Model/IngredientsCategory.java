package shivani.food_odering.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    private Restaurent restaurant;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<IngredientsItem> items = new ArrayList<IngredientsItem>();
}
