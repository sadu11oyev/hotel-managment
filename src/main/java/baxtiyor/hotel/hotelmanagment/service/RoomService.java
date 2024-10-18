package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;

import java.util.UUID;

public interface RoomService {
    Object getRoomsByHotelId(UUID hotelId);

    UUID addRoom(RoomReqDto reqDto);

    Object editRoom(UUID id, RoomReqDto reqDto);

    Object deleteRoom(UUID id);
}
