package baxtiyor.hotel.hotelmanagment.repo;

import baxtiyor.hotel.hotelmanagment.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}