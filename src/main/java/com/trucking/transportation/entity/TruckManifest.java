package com.trucking.transportation.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "MANIFEST")
public class TruckManifest {

    @Id @GeneratedValue @Column(name = "MANIFEST_ID") private Long truckManifestID;

//    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Truck.class, optional = false)
//    @JoinColumn(name = "TRUCK_ID", nullable = false) @Column(name = "TRUCK")
//    private Truck truck;

    @OneToMany(targetEntity = Package.class, cascade = CascadeType.ALL) // TODO
    //@JoinColumn(name = "PACKAGE.PACKAGE_ID", nullable = false)
    @Column(name = "PACKAGES")
    private List<Package> packages;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRUCK_ID")
    private Truck truck;

    TruckManifest(Truck truck) { this.truck = truck; }

    public void setPackages(Package p) {
        packages.add(p);
    }
}
