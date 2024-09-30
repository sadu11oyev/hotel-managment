package baxtiyor.hotel.hotelmanagment.mapper;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.RoomResDto;
import baxtiyor.hotel.hotelmanagment.entity.Room;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    Room toEntity(RoomReqDto roomReqDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(RoomReqDto roomReqDto, @MappingTarget Room room);

    @Mapping(source = "hotelId", target = "hotel.id")
    Room toEntity(RoomResDto roomResDto);

    @Mapping(source = "hotel.id", target = "hotelId")
    RoomResDto toResDto(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "hotelId", target = "hotel.id")
    Room partialUpdate(RoomResDto roomResDto, @MappingTarget Room room);
}