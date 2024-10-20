package baxtiyor.hotel.hotelmanagment.dto.res;

import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Order}
 */
@Value
public class OrderResDto implements Serializable {
    Integer roomRoomNumber;
    Integer roomFloor;
    Integer rate;
    String userEmail;
    String comment;
    Double roomPrice;
    RoomType roomTypeRoom;
    Double totalPrice;
    LocalDate checkIn;
    LocalDate checkOut;
    LocalDateTime createdAt;
}