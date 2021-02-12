package com.trucking.transportation.repository;

import com.trucking.transportation.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
    Truck findByTruckID(Long truckID);
}
