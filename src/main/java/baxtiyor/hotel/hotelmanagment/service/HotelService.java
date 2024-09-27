package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.HotelReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.HotelResDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface HotelService {
    Page<HotelResDto> getHotels(int page, int size);

    UUID addHotel(HotelReqDto reqDto);

    Object editHotel(UUID id, HotelReqDto reqDto);

    Object deleteHotel(UUID id);
}
