package com.trucking.transportation.repository;

import com.trucking.transportation.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    Package findByPackageID(Long packageID);
}
