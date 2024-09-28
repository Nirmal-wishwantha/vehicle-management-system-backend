package lk.riyapola.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private String description;
  private String imgPath;

    public Vehicle(Integer id, String brand, String model, String description) {
        this.brand = brand;
        this.model = model;
        this.description = description;
    }
}
