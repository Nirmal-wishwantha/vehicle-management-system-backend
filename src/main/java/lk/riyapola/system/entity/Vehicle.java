package lk.riyapola.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String brand;
  private String model;
  private double price;
  private String description;
  private String imgPath;

    @OneToMany(mappedBy = "vehicleId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public Vehicle(Integer id, String brand, String model, double price, String description) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.description = description;
    }
}
