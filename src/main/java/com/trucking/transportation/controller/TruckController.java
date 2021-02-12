package com.trucking.transportation.controller;

import com.trucking.transportation.entity.Truck;
import com.trucking.transportation.repository.TruckRepository;
import com.trucking.transportation.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delivery")
public class TruckController {

    private final TruckRepository repository;
    TruckController(TruckRepository repository) { this.repository = repository; }

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/trucks")
    public ResponseEntity<Object> getTrucks() {
        return new ResponseEntity<>(deliveryService.getAllTrucks(), HttpStatus.OK);
    }

    @GetMapping("/trucks/{id}")
    public ResponseEntity<Object> getTruck(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(deliveryService.getTruckById(id), HttpStatus.OK);
    }

    @PostMapping("/trucks")
    public ResponseEntity<Object> addTruck(@RequestBody Truck t) {
        repository.save(t);
        return new ResponseEntity<>("Truck was added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/trucks")
    public ResponseEntity<Object> updateTruck(@RequestBody Truck t) {
        deliveryService.updateTruck(t);
        return new ResponseEntity<>("Truck was updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/trucks/{id}")
    public ResponseEntity<Object> deleteTruck(@PathVariable(name = "id") Long id) {
        deliveryService.deleteTruck(id);
        return new ResponseEntity<>("Truck was deleted successfully", HttpStatus.OK);
    }
}
