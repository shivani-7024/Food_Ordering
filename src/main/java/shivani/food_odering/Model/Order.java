package shivani.food_odering.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Restaurent restaurent;

    @ManyToOne
    private User customer;

    private long totalAmount;
    private String status;
    private Date createdAt;

    @ManyToOne
    private Address address;

    @OneToMany
    private List<OrderItem> orderItems;

    private int totalItems;
    private int totalPrice;

}