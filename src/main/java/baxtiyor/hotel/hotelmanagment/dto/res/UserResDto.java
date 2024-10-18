package baxtiyor.hotel.hotelmanagment.dto.res;

import baxtiyor.hotel.hotelmanagment.entity.enums.RoleName;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.User}
 */
@Value
public class UserResDto implements Serializable {
    UUID id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    List<RoleName> roleRoleNames;
}