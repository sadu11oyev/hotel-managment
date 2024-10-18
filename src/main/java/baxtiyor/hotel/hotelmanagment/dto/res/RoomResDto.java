package baxtiyor.hotel.hotelmanagment.dto.res;

import baxtiyor.hotel.hotelmanagment.entity.enums.RoomStatus;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Room}
 */
@Value
public class RoomResDto implements Serializable {
    @NotNull
    UUID id;
    @NotNull
    Integer roomNumber;
    @NotNull
    Integer floor;
    @NotNull
    Double price;
    UUID hotelId;
    @NotNull
    RoomType typeRoom;
    @NotNull
    RoomStatus statusRoom;
}