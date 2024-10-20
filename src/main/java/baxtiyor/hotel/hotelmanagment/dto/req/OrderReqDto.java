package baxtiyor.hotel.hotelmanagment.dto.req;

import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Order}
 */
@Value
public class OrderReqDto implements Serializable {
    RoomType roomTypeRoom;
    UUID userId;
    LocalDate checkIn;
    LocalDate checkOut;
}