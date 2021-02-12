package com.trucking.transportation.repository;

import com.trucking.transportation.entity.TruckManifest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckManifestRepository extends JpaRepository<TruckManifest, Long> {
    TruckManifest findByTruckManifestID(Long truckManifestID);
}
