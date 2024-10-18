package baxtiyor.hotel.hotelmanagment.mapper;

import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.UserResDto;
import baxtiyor.hotel.hotelmanagment.entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserReqDto userReqDto);

    UserResDto toResDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserReqDto userReqDto, @MappingTarget User user);
}