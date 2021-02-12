package com.trucking.transportation.service;

import com.trucking.transportation.entity.Package;
import com.trucking.transportation.entity.Truck;
import com.trucking.transportation.entity.TruckManifest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeliveryService {
    List<Package> getAllPackages();
    List<Truck> getAllTrucks();
    List<TruckManifest> getAllManifests();
    List<Package> getAllLoadedPackages(Long truckManifestID);

    void createPackage(Package p);
    Package getPackageById(Long id);
    void updatePackage(Package p);
    void deletePackage(Long packageID);

    void createTruck(Truck t);
    Truck getTruckById(Long truckID);
    void updateTruck(Truck t);
    void deleteTruck(Long truckID);

    void createTruckManifest(TruckManifest tm);
    void addTruckToManifest(Long manifestID, Truck t);
    TruckManifest getTruckManifestById(Long truckManifestID);
    void updateTruckManifest(TruckManifest tm);
    void deleteTruckManifest(Long truckManifestID);

    void addPackageToManifest(Long truckManifestID, Package p);
    void load(Package p, Long truckManifestID);
    void deliver(Package p);

}
