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

@Service
public class VehicleService {

    @Autowired
    VehicleRepo vehicleRepo;


    public VehicleDto vehicalSave(VehicleDto vehicleDto){
        Vehicle save = vehicleRepo.save(new Vehicle(vehicleDto.getId(), vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getDescription()));
        return new VehicleDto(save.getId(),save.getBrand(),save.getModel(),save.getDescription());
    }


    public boolean vehicalDelete(Integer vehicleId){
        if (vehicleRepo.existsById(vehicleId)) {
            vehicleRepo.deleteById(vehicleId);
            return true;
        }
    return false;
    }


    public VehicleDto vehicalUpdate(Integer vehicleId,VehicleDto vehicleDto){

        if (vehicleRepo.existsById(vehicleId)){
            Vehicle updateVehicle = vehicleRepo.save(new Vehicle(vehicleDto.getId(), vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getDescription()));

            return new VehicleDto(updateVehicle.getId(),updateVehicle.getBrand(),updateVehicle.getModel(),updateVehicle.getDescription());
        }
        return null;
    }


    public List<VehicleDto> vehicalGet(){
        List<Vehicle> all = vehicleRepo.findAll();
        List<VehicleDto> allVehical = new ArrayList<>();

        for (Vehicle vehicle : all) {
            allVehical.add(new VehicleDto(vehicle.getId(),vehicle.getBrand(),vehicle.getModel(),vehicle.getDescription()));
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

}
