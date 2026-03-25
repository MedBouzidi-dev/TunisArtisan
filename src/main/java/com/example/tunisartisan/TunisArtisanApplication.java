package com.example.tunisartisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TunisArtisanApplication {

    public static void main(String[] args) {
        SpringApplication.run(TunisArtisanApplication.class, args);
    }

}
