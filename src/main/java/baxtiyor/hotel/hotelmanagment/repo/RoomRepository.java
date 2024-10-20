package baxtiyor.hotel.hotelmanagment.repo;

import baxtiyor.hotel.hotelmanagment.entity.Room;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    @Query(nativeQuery = true, value = "select * from room where hotel_id=:hotelId")
    List<Room> findAllByHotelId(UUID hotelId);

    @Query(nativeQuery = true, value = "select * from room where hotel_id = :hotelId and type_room = :type")
    List<Room> findAllByHotelIdAndType(@Param("hotelId") UUID hotelId, @Param("type") RoomType type);

    @Query(nativeQuery = true, value = "SELECT *FROM room WHERE type_room =:roomType  AND id NOT IN (\n" +
            "    SELECT o.room_id FROM orders o WHERE NOT ( (o.check_out <=:checkIn) OR (o.check_in >=:checkOut)))")
    List<Room> allEmptyRooms(RoomType roomType, LocalDate checkIn, LocalDate checkOut);
}