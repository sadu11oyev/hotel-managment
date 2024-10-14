package baxtiyor.hotel.hotelmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HotelManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelManagmentApplication.class, args);
    }

}
