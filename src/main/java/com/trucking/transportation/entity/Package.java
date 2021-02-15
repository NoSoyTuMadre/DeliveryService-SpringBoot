package com.trucking.transportation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name="PACKAGE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PACKAGE_ID") private Long packageID;
    @Column(name = "SRC_ADDRESS") private String sourceAddress;
    @Column(name = "DEST_ADDRESS") private String destinationAddress;
    @Column(name = "DELIVERY_TIME") private LocalDateTime deliveryTime;

    @ManyToOne(targetEntity = TruckManifest.class)
    @JoinColumn(name = "MANIFEST.MANIFEST_ID")
    private TruckManifest truckManifest;

    @OneToOne(targetEntity = Truck.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRUCK.TRUCK_ID")
    private Truck truck;

    Package(String sourceAddress, String destinationAddress) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
    }
}
