package com.trucking.transportation.controller;

import com.trucking.transportation.entity.Package;
import com.trucking.transportation.entity.TruckManifest;
import com.trucking.transportation.repository.TruckManifestRepository;
import com.trucking.transportation.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/delivery/trucks")
public class TruckManifestController {

    private final TruckManifestRepository manifestRepository;

    TruckManifestController(TruckManifestRepository truckManifestRepository) { this.manifestRepository = truckManifestRepository; }

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/manifests/packages/{id}")
    List<Package> getLoadedPackages(@PathVariable Long id) {
        for (TruckManifest tm : manifestRepository.findAll()) {
            if (tm.getTruckManifestID().equals(id)) {
                return manifestRepository.findByTruckManifestID(id).getPackages();
            }
        }
        throw new RuntimeException("Truck Manifest ID " + id + " does not exist");
    }

    @PostMapping("/manifests/packages/{id}")
    public ResponseEntity<Object> addPackageToManifest(@PathVariable("id") Long id, @RequestBody Package p) {
        deliveryService.load(p, id);
        return new ResponseEntity<>("Package was added to truck manifest successfully", HttpStatus.CREATED);
    }

    @GetMapping("/manifests")
    public ResponseEntity<Object> getTruckManifests() {
        return new ResponseEntity<>(deliveryService.getAllManifests(), HttpStatus.OK);
    }

    @GetMapping("/manifests/{id}")
    public ResponseEntity<Object> getTruckManifest(@PathVariable("id") Long id) {
        return new ResponseEntity<>(deliveryService.getTruckManifestById(id), HttpStatus.OK);
    }

    @PostMapping("/manifests")
    public ResponseEntity<Object> addTruck(@RequestBody TruckManifest tm) {
        deliveryService.createTruckManifest(tm);
        return new ResponseEntity<>("Truck manifest was created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/manifests/{id}")
    public ResponseEntity<Object> updateTruckManifest(@PathVariable("id") Long id, @RequestBody TruckManifest tm) {
        deliveryService.updateTruckManifest(id, tm);
        return new ResponseEntity<>("Truck manifest was updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/manifests/{id}")
    public ResponseEntity<Object> deleteTruckManifest(@PathVariable("id") Long id) {
        deliveryService.deleteTruckManifest(id);
        return new ResponseEntity<>("Truck manifest was deleted successfully", HttpStatus.OK);
    }
}
