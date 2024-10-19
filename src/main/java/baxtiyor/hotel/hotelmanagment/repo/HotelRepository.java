package baxtiyor.hotel.hotelmanagment.repo;

import baxtiyor.hotel.hotelmanagment.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    @Query(nativeQuery = true,value = "select * from hotel where is_delete:=false")
    List<Hotel> findActiveHotels();


    @Modifying
    @Query(nativeQuery = true, value = """
        UPDATE hotel set is_delete=true where id=:id;
        update room set is_delete=true where hotel_id=:id;
    """)
    void deleteByIdAndRooms(UUID id);
}