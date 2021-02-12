package com.trucking.transportation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class TransportationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);
    }

}
