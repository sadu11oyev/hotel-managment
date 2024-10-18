package baxtiyor.hotel.hotelmanagment.dto.req;

import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Room}
 */
@Value
public class RoomReqDto implements Serializable {
    @NotNull
    Integer roomNumber;
    @NotNull
    Integer floor;
    @NotNull
    Double price;
    UUID hotelId;
    @NotNull
    RoomType typeRoom;
}