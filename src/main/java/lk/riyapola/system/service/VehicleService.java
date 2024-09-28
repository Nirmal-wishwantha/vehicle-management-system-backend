package lk.riyapola.system.service;

import lk.riyapola.system.dto.VehicleDto;
import lk.riyapola.system.entity.Vehicle;
import lk.riyapola.system.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepo vehicleRepo;


    public VehicleDto vehicalSave(VehicleDto vehicleDto){
        Vehicle save = vehicleRepo.save(new Vehicle(vehicleDto.getId(), vehicleDto.getBrand(), vehicleDto.getModel(),vehicleDto.getPrice(), vehicleDto.getDescription()));
        return new VehicleDto(save.getId(),save.getBrand(),save.getModel(), save.getPrice(),save.getDescription());
    }


    public boolean vehicalDelete(Integer vehicleId){
        if (vehicleRepo.existsById(vehicleId)) {
            vehicleRepo.deleteById(vehicleId);
            return true;
        }
    return false;
    }


    public VehicleDto vehicleUpdate(Integer vehicleId, VehicleDto vehicleDto) {

        Optional<Vehicle> getVehicle = vehicleRepo.findById(vehicleId);

        if (getVehicle.isPresent()) {
            Vehicle updateVehicle = getVehicle.get();

            updateVehicle.setBrand(vehicleDto.getBrand());
            updateVehicle.setModel(vehicleDto.getModel());
            updateVehicle.setPrice(vehicleDto.getPrice());
            updateVehicle.setDescription(vehicleDto.getDescription());

            Vehicle updatedVehicle = vehicleRepo.save(updateVehicle);

            return new VehicleDto(updatedVehicle.getId(), updatedVehicle.getBrand(), updatedVehicle.getModel(),updatedVehicle.getPrice(), updatedVehicle.getDescription());
        }

        return null;
    }



    public List<VehicleDto> vehicalGet(){
        List<Vehicle> all = vehicleRepo.findAll();
        List<VehicleDto> allVehical = new ArrayList<>();

        for (Vehicle vehicle : all) {
            allVehical.add(new VehicleDto(vehicle.getId(),vehicle.getBrand(),vehicle.getModel(),vehicle.getPrice(),vehicle.getDescription()));
        }
        return allVehical;
    }

    public int imageUpload(MultipartFile file,Integer vehicleId) throws IOException {
        String fileName = file.getOriginalFilename();
        Path uploadPath = Paths.get("upload/", fileName);
        Files.createDirectories(uploadPath.getParent());
        Files.write(uploadPath, file.getBytes());

        Vehicle vehicle = vehicleRepo.findById(vehicleId).get();
        vehicle.setImgPath(uploadPath.toString());
        Vehicle save = vehicleRepo.save(vehicle);

        if (save != null){
            return 1;
        }
        return 0;
    }


    public byte[]  getImage(Integer Id) throws IOException {
        Vehicle vehicle = vehicleRepo.findById(Id).get();
        Path path = Paths.get(vehicle.getImgPath());
        return Files.readAllBytes(path);

    }

    public String deleteImage(Integer Id) {
        if (vehicleRepo.existsById(Id)) {
            Optional<Vehicle> byId = vehicleRepo.findById(Id);
            byId.get().setImgPath(null);
            vehicleRepo.save(byId.get());
        }
        return new String("Image deleted successfully");
    }


    public String updateImage(Integer id, MultipartFile file){
        if (vehicleRepo.existsById(id)) {
            Vehicle vehicle = vehicleRepo.findById(id).get();

            vehicle.setImgPath(file.getOriginalFilename());
            Vehicle save = vehicleRepo.save(vehicle);
            return new String("Image updated successfully");

        }
        return new String("Image not updated ");


    }


}
