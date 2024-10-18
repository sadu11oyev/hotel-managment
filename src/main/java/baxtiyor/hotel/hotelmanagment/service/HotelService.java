package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.HotelReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.HotelResDto;

import java.util.List;
import java.util.UUID;

public interface HotelService {
    List<HotelResDto> getHotels();

    UUID addHotel(HotelReqDto reqDto);

    Object editHotel(UUID id, HotelReqDto reqDto);

    Object deleteHotel(UUID id);
}
