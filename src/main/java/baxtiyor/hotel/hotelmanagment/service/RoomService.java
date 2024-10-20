package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.RoomResDto;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface RoomService {
    Page<RoomResDto> getRoomsByHotelId(int page, int size, UUID hotelId);

    UUID addRoom(RoomReqDto reqDto);

    Object editRoom(UUID id, RoomReqDto reqDto);

    Object deleteRoom(UUID id);

    Page<RoomResDto> getRoomsByHotelIdAndType(int page, int size, UUID hotelId, RoomType type);
}
