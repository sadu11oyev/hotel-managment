package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.dto.req.HotelReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.HotelResDto;
import baxtiyor.hotel.hotelmanagment.entity.Hotel;
import baxtiyor.hotel.hotelmanagment.mapper.HotelMapper;
import baxtiyor.hotel.hotelmanagment.repo.HotelRepository;
import baxtiyor.hotel.hotelmanagment.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository repository;
    private final HotelMapper hotelMapper;
    @Override
    public List<HotelResDto> getHotels() {
        List<Hotel> hotelList = repository.findActiveHotels();
        return hotelList.stream().map(hotelMapper::toDto).toList();
    }

    @Override
    public UUID addHotel(HotelReqDto reqDto) {
        Hotel hotel = hotelMapper.toEntity(reqDto);
        hotel.setIsDelete(false);
        repository.save(hotel);
        return hotel.getId();
    }

    @Override
    public Object editHotel(UUID id, HotelReqDto reqDto) {
        Optional<Hotel> opt = repository.findById(id);
        if (opt.isPresent()){
            Hotel currentHotel = opt.get();
            currentHotel.setName(reqDto.getName());
            currentHotel.setAddress(reqDto.getAddress());
            currentHotel.setWebsite(reqDto.getWebsite());
            currentHotel.setPhoneNumber(reqDto.getPhoneNumber());
            repository.save(currentHotel);
            return hotelMapper.toDto(currentHotel);
        }else {
            return "Hotel not found";
        }
    }

    @Override
    public Object deleteHotel(UUID id) {
        Optional<Hotel> opt = repository.findById(id);
        if (opt.isPresent()){
            Hotel currentHotel = opt.get();
            repository.deleteByIdAndRooms(id);
            return hotelMapper.toDto(currentHotel);
        }else {
            return "Hotel not found";
        }
    }
}
