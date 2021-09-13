package com.exam.api.Location;

import com.exam.api.Location.Repository.LocationRepository;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LocationApplication {
        
	public static void main(String[] args) {
            ApplicationContext ctx = SpringApplication.run(LocationApplication.class, args);
            LocationRepository archive = ctx.getBean(LocationRepository.class);
            try {
                //Se va a cargar la informacion de las ciudades
                archive.preLoadInformation();
            } catch (IOException ex) {
                Logger.getLogger(LocationApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
