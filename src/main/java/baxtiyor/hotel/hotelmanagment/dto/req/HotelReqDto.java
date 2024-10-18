package baxtiyor.hotel.hotelmanagment.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Hotel}
 */
@Value
public class HotelReqDto implements Serializable {
    @NotNull
    String name;
    @NotNull
    String address;
    @NotNull
    String phoneNumber;
    @NotNull
    String website;
}