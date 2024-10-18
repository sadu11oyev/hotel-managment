package baxtiyor.hotel.hotelmanagment.repo;

import baxtiyor.hotel.hotelmanagment.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    @Query(nativeQuery = true, value = "select * from room where hotel_id=:hotelId")
    List<Room> findAllByHotelId(UUID hotelId);
}