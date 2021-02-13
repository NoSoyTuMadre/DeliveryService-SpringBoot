package com.trucking.transportation.serviceImpl;

import com.trucking.transportation.entity.Package;
import com.trucking.transportation.entity.Truck;
import com.trucking.transportation.entity.TruckManifest;
import com.trucking.transportation.repository.PackageRepository;
import com.trucking.transportation.repository.TruckManifestRepository;
import com.trucking.transportation.repository.TruckRepository;
import com.trucking.transportation.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    TruckRepository truckRepository;

    @Autowired
    TruckManifestRepository truckManifestRepository;

    @Override
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public List<TruckManifest> getAllManifests() {
        return truckManifestRepository.findAll();
    }

    @Override
    public List<Package> getAllLoadedPackages(Long truckManifestID) {
        for (TruckManifest tm : truckManifestRepository.findAll()) {
            if (tm.getTruckManifestID().equals(truckManifestID)) {
                return tm.getPackages();
            }
        }
        throw new RuntimeException("A truck manifest with the ID of " + truckManifestID + " could not be found");
    }

    @Override
    public void createPackage(Package p) {
        if (p.getPackageID() != null) {
            throw new RuntimeException("A package with the ID of " + p.getPackageID() + " already exists");
        } else { packageRepository.save(p); }
    }

    @Override
    public Package getPackageById(Long id) {
        Optional<Package> packageOptional = packageRepository.findById(id);
        if (packageOptional.isPresent()) {
            return packageOptional.get();
        } else { throw new RuntimeException("A package with the ID of " + id + " could not be found"); }
    }

    @Override
    public void updatePackage(Package p) {
        Package pkg = getPackageById(p.getPackageID());
        if (pkg == null) {
            throw new RuntimeException("A package with the ID of " + p.getPackageID() + " could not be found");
        } else {
            packageRepository.save(p);
        }
    }

    @Override
    public void deletePackage(Long packageID) {
        Package pkg = packageRepository.findByPackageID(packageID);
        if (pkg != null) {
            packageRepository.delete(pkg);
        } else {
            throw new RuntimeException("A package with the ID of " + packageID + " could not be found");
        }
    }

    @Override
    public void createTruck(Truck t) {
        if (t.getTruckID() != null) {
            throw new RuntimeException("A truck with the ID of " + t.getTruckID() + " already exists");
        } else { truckRepository.save(t); }
    }

    @Override
    public Truck getTruckById(Long truckID) {
        Optional<Truck> truckOptional = truckRepository.findById(truckID);
        if (truckOptional.isPresent()) {
            return truckOptional.get();
        } else { throw new RuntimeException("A truck with the ID of " + truckID + " could not be found"); }
    }

    @Override
    public void updateTruck(Truck t) {
        Truck trk = getTruckById(t.getTruckID());
        if (trk == null) {
            throw new RuntimeException("A truck with the ID of " + t.getTruckID() + " could not be found");
        } else { truckRepository.save(t); }
    }

    @Override
    public void deleteTruck(Long truckID) {
        Truck trk = truckRepository.findByTruckID(truckID);
        if (trk != null) {
            truckRepository.delete(trk);
        } else {
            throw new RuntimeException("A truck with the ID of " + truckID + " could not be found");
        }
    }

    @Override
    public void createTruckManifest(TruckManifest tm) {
        if (tm.getTruckManifestID() != null) {
            throw new RuntimeException("A truck manifest with the ID of " + tm.getTruckManifestID() + " already exists");
        } else { truckManifestRepository.save(tm); }
    }

    @Override
    public void addTruckToManifest(Long manifestID,Long truckID) {
        TruckManifest tm = truckManifestRepository.findByTruckManifestID(manifestID);
        Truck t = truckRepository.findByTruckID(truckID);
        if (tm != null && t != null) {
            tm.setTruck(t);
            t.setTruckManifests(tm);
        } else if (tm != null) {
            throw new RuntimeException("A truck with the ID of " + truckID + " does not exist");
        } else {
            throw new RuntimeException("A truck manifest with the ID of " + manifestID + " does not exist");
        }
    }

    @Override
    public TruckManifest getTruckManifestById(Long truckManifestID) {
        Optional<TruckManifest> truckManifestOptional = truckManifestRepository.findById(truckManifestID);
        if (truckManifestOptional.isPresent()) {
            return truckManifestOptional.get();
        } else { throw new RuntimeException("A truck manifest with the ID of " + truckManifestID + " could not be found"); }
    }

    @Override
    public void updateTruckManifest(TruckManifest tm) {
        TruckManifest truckManifest = getTruckManifestById(tm.getTruckManifestID());
        if (truckManifest == null) {
            throw new RuntimeException("A truck manifest with the ID of " + tm.getTruckManifestID() + " could not be found");
        } else { truckManifestRepository.save(tm); }
    }

    @Override
    public void deleteTruckManifest(Long truckManifestID) {
        TruckManifest tm = truckManifestRepository.findByTruckManifestID(truckManifestID);
        if (tm != null) {
            truckManifestRepository.delete(tm);
        } else {
            throw new RuntimeException("A truck manifest with the ID of " + truckManifestID + " could not be found");
        }
    }

    @Override
    public void addPackageToManifest(Long truckManifestID, Package p) {
        for (TruckManifest truckManifest : truckManifestRepository.findAll()) {
            if (truckManifest.getTruckManifestID().equals(truckManifestID)) {
                if (p.getPackageID() != null) {
                    truckManifest.setPackages(p);
                }
            }
        }
        throw new RuntimeException("A truck manifest with the ID of " + truckManifestID + " could not be found");
    }

    @Override
    public void load(Package p, Long truckManifestID) {
        TruckManifest tm = truckManifestRepository.findByTruckManifestID(truckManifestID);
        if (tm != null) {
            if (p.getPackageID() == null) {
                createPackage(p);
            }
            if (!p.getTruckManifest().equals(tm)) {
                tm.setPackages(p);
            } else {
                throw new RuntimeException("A package with the ID of " + p.getPackageID() + " is already loaded");
            }
        } else {
            throw new RuntimeException("A truck manifest with the ID of " + truckManifestID + " could not be found");
        }
    }

    @Override
    public void deliver(Package p) {
        if (p.getDeliveryTime() != null) {
            for (Package pkg : p.getTruckManifest().getPackages()) {
                if (pkg.getPackageID().equals(p.getPackageID())) {
                    p.getTruckManifest().getPackages().remove(p);
                    p.setDeliveryTime(LocalDateTime.now());
                }
            }
            throw new RuntimeException("Package " + p.getPackageID() + " was never loaded");
        }
        throw new RuntimeException("Package " + p.getPackageID() + " has already been delivered");
    }
}
