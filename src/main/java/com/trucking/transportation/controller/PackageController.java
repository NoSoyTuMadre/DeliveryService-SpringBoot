package com.trucking.transportation.controller;

import com.trucking.transportation.entity.Package;
import com.trucking.transportation.repository.PackageRepository;
import com.trucking.transportation.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delivery")
public class PackageController {

    private final PackageRepository packageRepository;
    PackageController(PackageRepository repository) { this.packageRepository = repository; }

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/packages")
    public ResponseEntity<Object> getPackages() {
        return new ResponseEntity<>(deliveryService.getAllPackages(), HttpStatus.OK);
    }

    @GetMapping("/packages/{id}")
    public ResponseEntity<Object> getPackage(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(deliveryService.getPackageById(id), HttpStatus.OK);
    }

    @PostMapping("/packages")
    public ResponseEntity<Object> createPackage(@RequestBody Package p) {
        deliveryService.createPackage(p);
        return new ResponseEntity<>("Package was created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/packages")
    public ResponseEntity<Object> updatePackage(@RequestBody Package p) {
        deliveryService.updatePackage(p);
        return new ResponseEntity<>("Package was updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/packages/{id}")
    public ResponseEntity<Object> deletePackage(@PathVariable(name = "id") Long id) {
        deliveryService.deletePackage(id);
        return new ResponseEntity<>("Package was deleted successfully", HttpStatus.OK);
    }
}
