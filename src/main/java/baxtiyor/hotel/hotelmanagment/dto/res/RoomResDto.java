package baxtiyor.hotel.hotelmanagment.dto.res;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Room}
 */
@Value
public class RoomResDto implements Serializable {
    UUID id;
    Integer roomNumber;
    Integer floor;
    Double price;
    UUID hotelId;
    RoomType typeRoom;
}