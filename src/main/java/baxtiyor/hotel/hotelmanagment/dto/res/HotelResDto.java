package baxtiyor.hotel.hotelmanagment.dto.res;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Hotel}
 */
@Value
public class HotelResDto implements Serializable {
    UUID id;
    String name;
    String address;
    String phoneNumber;
    String email;
    String website;
}