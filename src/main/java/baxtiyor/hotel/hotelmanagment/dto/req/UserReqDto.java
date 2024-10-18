package baxtiyor.hotel.hotelmanagment.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.User}
 */
@Value
public class UserReqDto implements Serializable {
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    String phoneNumber;
}