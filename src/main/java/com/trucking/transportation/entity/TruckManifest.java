package com.trucking.transportation.entity;

import javax.persistence.*;

import com.trucking.transportation.repository.TruckManifestRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "MANIFEST")
public class TruckManifest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "MANIFEST_ID") private Long truckManifestID;

//    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Truck.class, optional = false)
//    @JoinColumn(name = "TRUCK_ID", nullable = false) @Column(name = "TRUCK")
//    private Truck truck;

    //@JoinColumn(name = "PACKAGE.PACKAGE_ID", nullable = false)
    @OneToMany(targetEntity = Package.class, cascade = CascadeType.ALL) // TODO
    @Column(name = "PACKAGES")
    private List<Package> packages;

    @ManyToOne(targetEntity = Truck.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRUCK.TRUCK_ID")
    private Truck truck;

    TruckManifest(Truck truck) { this.truck = truck; }

    public void setPackages(Package p) {
        this.packages.add(p);
    }
}
