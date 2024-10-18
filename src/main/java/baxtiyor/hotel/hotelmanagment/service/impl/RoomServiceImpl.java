package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;
import baxtiyor.hotel.hotelmanagment.entity.Hotel;
import baxtiyor.hotel.hotelmanagment.entity.Room;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomStatus;
import baxtiyor.hotel.hotelmanagment.mapper.RoomMapper;
import baxtiyor.hotel.hotelmanagment.repo.HotelRepository;
import baxtiyor.hotel.hotelmanagment.repo.RoomRepository;
import baxtiyor.hotel.hotelmanagment.service.RoomService;
import lombok.RequiredArgsConstructor;
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
    public Object getRoomsByHotelId(UUID hotelId) {
        Optional<Hotel> opt = hotelRepo.findById(hotelId);
        if (opt.isPresent()){
            Hotel hotel = opt.get();
            if (!hotel.getIsDelete()){
                List<Room> rooms = repository.findAllByHotelId(hotel.getId());
                return rooms.stream().map(roomMapper::toResDto).toList();
            }else {
                return "Hotel is delete";
            }
        }else {
            return "Hotel not found";
        }
    }

    @Override
    public UUID addRoom(RoomReqDto reqDto) {
        Room room = roomMapper.toEntity(reqDto);
        room.setIsDelete(false);
        room.setStatusRoom(RoomStatus.AVAILABLE);
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
}
