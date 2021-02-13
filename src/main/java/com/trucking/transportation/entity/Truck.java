package com.trucking.transportation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "TRUCK")
public class Truck {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "TRUCK_ID") private Long truckID;
    private Long odometer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MANIFEST.MANIFEST_ID")
    @Column(name = "MANIFESTS")
    private List<TruckManifest> truckManifests;

    Truck(Long odometer) { this.odometer = odometer; }

    public void setTruckManifests(TruckManifest tm) {
        this.truckManifests.add(tm);
    }

}
