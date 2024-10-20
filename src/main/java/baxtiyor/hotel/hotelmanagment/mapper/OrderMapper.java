package baxtiyor.hotel.hotelmanagment.mapper;

import baxtiyor.hotel.hotelmanagment.dto.req.OrderReqDto;
import baxtiyor.hotel.hotelmanagment.dto.req.RateDto;
import baxtiyor.hotel.hotelmanagment.dto.res.OrderResDto;
import baxtiyor.hotel.hotelmanagment.entity.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderReqDto orderReqDto);

    OrderReqDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderReqDto orderReqDto, @MappingTarget Order order);

    @Mapping(source = "userEmail", target = "user.email")
    @Mapping(source = "roomTypeRoom", target = "room.typeRoom")
    @Mapping(source = "roomPrice", target = "room.price")
    @Mapping(source = "roomFloor", target = "room.floor")
    @Mapping(source = "roomRoomNumber", target = "room.roomNumber")
    Order toEntity(OrderResDto orderResDto);

    @InheritInverseConfiguration(name = "toEntity")
    OrderResDto toDto1(Order order);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderResDto orderResDto, @MappingTarget Order order);

    Order toEntity(RateDto rateDto);

    RateDto toDto2(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(RateDto rateDto, @MappingTarget Order order);
}