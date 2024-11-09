package storeManagementTool.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public Product() {

    }
}
