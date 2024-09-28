package lk.riyapola.system.controller;

import lk.riyapola.system.dto.VehicleDto;
import lk.riyapola.system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDto> vehicleSave(@RequestBody VehicleDto vehicleDto){
        VehicleDto save = vehicleService.vehicalSave(vehicleDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }



    @DeleteMapping("/{vehicalId}")
    public String vehicleDelete(@PathVariable Integer vehicalId){
        boolean b = vehicleService.vehicalDelete(vehicalId);
        return new String("Deleted");

    }


    @GetMapping
    public List<VehicleDto> vehicleGet(){
        List<VehicleDto> allVehicle = vehicleService.vehicalGet();
        return allVehicle;
    }

    @PutMapping("/{vehicalId}")
    public ResponseEntity<VehicleDto> vehicleUpdate(@PathVariable Integer vehicalId, @RequestBody VehicleDto vehicle){
        VehicleDto vehicleUpdate = vehicleService.vehicleUpdate(vehicalId, vehicle);

        if (vehicleUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleUpdate, HttpStatus.OK);

    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> imageUpload(@RequestParam("file") MultipartFile file, @PathVariable Integer id) throws IOException {
        int i = vehicleService.imageUpload(file, id);

        if (i==1){
            return new ResponseEntity<String>("Upload Success !",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Upload Failed",HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/get/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
        byte[] image = vehicleService.getImage(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/image/{id}")
    public String imageDelete(@PathVariable Integer id){
        String s = vehicleService.deleteImage(id);
        return s;
    }

    @PutMapping("/update/image/{id}")

    public String updateImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file){
        String s = vehicleService.updateImage(id, file);
        return s;
    }




}
