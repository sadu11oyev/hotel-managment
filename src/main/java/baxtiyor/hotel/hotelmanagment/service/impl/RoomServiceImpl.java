package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.RoomResDto;
import baxtiyor.hotel.hotelmanagment.entity.Hotel;
import baxtiyor.hotel.hotelmanagment.entity.Room;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import baxtiyor.hotel.hotelmanagment.mapper.RoomMapper;
import baxtiyor.hotel.hotelmanagment.repo.HotelRepository;
import baxtiyor.hotel.hotelmanagment.repo.RoomRepository;
import baxtiyor.hotel.hotelmanagment.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;
    private final RoomMapper roomMapper;
    private final HotelRepository hotelRepo;

    @Override
    public Page<RoomResDto> getRoomsByHotelId(int page, int size, UUID hotelId) {
        Pageable pageable = PageRequest.of(page,size);
        Hotel hotel = hotelRepo.findById(hotelId).get();
        List<Room> rooms = repository.findAllByHotelId(hotel.getId());
        List<RoomResDto> roomResDtos = rooms
                .stream().map(roomMapper::toResDto)
                .toList();
        return new PageImpl<>(roomResDtos, pageable, roomResDtos.size());
    }

    @Override
    public UUID addRoom(RoomReqDto reqDto) {
        Room room = roomMapper.toEntity(reqDto);
        room.setIsDelete(false);
        repository.save(room);
        return room.getId();
    }

    @Override
    public Object editRoom(UUID id, RoomReqDto reqDto) {
        Optional<Room> opt = repository.findById(id);
        if (opt.isPresent()){
            Room room = opt.get();
            room.setRoomNumber(reqDto.getRoomNumber());
            room.setFloor(reqDto.getFloor());
            room.setTypeRoom(reqDto.getTypeRoom());
            room.setPrice(reqDto.getPrice());
            room.setHotel(hotelRepo.findById(reqDto.getHotelId()).get());
            repository.save(room);
            return roomMapper.toResDto(room);
        }else {
            return "Room not found";
        }
    }

    @Override
    public Object deleteRoom(UUID id) {
        Optional<Room> opt=repository.findById(id);
        if (opt.isPresent()){
            Room room = opt.get();
            room.setIsDelete(true);
            repository.save(room);
            return roomMapper.toResDto(room);
        }else {
            return "Room not found";
        }
    }

    @Override
    public Page<RoomResDto> getRoomsByHotelIdAndType(int page, int size, UUID hotelId, RoomType type) {
        Pageable pageable = PageRequest.of(page,size);
        Hotel hotel = hotelRepo.findById(hotelId).get();
        List<Room> rooms = repository.findAllByHotelIdAndType(hotel.getId(),type);
        List<RoomResDto> roomResDtos = rooms
                .stream().map(roomMapper::toResDto)
                .toList();
        return new PageImpl<>(roomResDtos, pageable, roomResDtos.size());
    }
}
