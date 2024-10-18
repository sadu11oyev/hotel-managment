package baxtiyor.hotel.hotelmanagment.mapper;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.RoomResDto;
import baxtiyor.hotel.hotelmanagment.entity.Room;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    Room toEntity(RoomReqDto roomReqDto);

    RoomResDto toResDto(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(RoomReqDto roomReqDto, @MappingTarget Room room);
}