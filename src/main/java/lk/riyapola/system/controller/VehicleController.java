package lk.riyapola.system.controller;

import io.jsonwebtoken.Header;
import lk.riyapola.system.dto.VehicleDto;
import lk.riyapola.system.entity.Vehicle;
import lk.riyapola.system.repo.VehicleRepo;
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
import java.util.Optional;

import static org.springframework.web.servlet.mvc.method.RequestMappingInfo.paths;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    private VehicleRepo vehicleRepo;

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

//image
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
        // Call the service to get the image bytes
        byte[] image = vehicleService.getImage(id);

        // Determine the file type (assume it's JPEG for this example)
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);


        String imgUrl = vehicle.get().getImgPath();
        String fileExtension = getFileExtension(imgUrl);

        // Set appropriate content type based on the file extension
        MediaType mediaType = getMediaTypeForFileExtension(fileExtension);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    // Helper method to extract the file extension from the URL
    private String getFileExtension(String url) {
        return url.substring(url.lastIndexOf(".") + 1);
    }

    // Helper method to determine media type from file extension
    private MediaType getMediaTypeForFileExtension(String extension) {
        switch (extension.toLowerCase()) {
            case "png":
                return MediaType.IMAGE_PNG;
            case "gif":
                return MediaType.IMAGE_GIF;
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
                case "webp":
                    return MediaType.valueOf("image/webp");
            case "bmp":
                return MediaType.valueOf("image/bmp");
            default:
                return MediaType.APPLICATION_OCTET_STREAM; // Fallback for unknown types
        }
    }

    @DeleteMapping("/delete/image/{id}")
    public String imageDelete(@PathVariable Integer id){
        String s = vehicleService.deleteImage(id);
        return s;
    }

    @PutMapping("/update/image/{id}")
    public String updateImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        String s = vehicleService.updateImage(id, file);
        return s;

    }


}
