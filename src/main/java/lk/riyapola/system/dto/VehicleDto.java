package lk.riyapola.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {

    Integer id;
    String brand;
    String model;
    double price;
    String description;
    String imagePath;

    public VehicleDto(Integer id, String brand, String model, double price, String description) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.description = description;
    }
}
