package shivani.food_odering.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;

    @ManyToOne
    private Address address;

    @OneToOne
    private User owner;

    private String cuisineType;

    @Embedded
    private ContactInformation contactInformation;

    private String openingHours;

    @OneToMany(mappedBy = "restaurent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private LocalDateTime regiatrationDate;
    private boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurent", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();
}
