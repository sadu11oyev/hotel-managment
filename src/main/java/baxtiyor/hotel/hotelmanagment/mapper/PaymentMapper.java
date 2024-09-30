package baxtiyor.hotel.hotelmanagment.mapper;

import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.PaymentResDto;
import baxtiyor.hotel.hotelmanagment.entity.Payment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    Payment toEntity(PaymentReqDto paymentReqDto);

    PaymentReqDto toDto(Payment payment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment partialUpdate(PaymentReqDto paymentReqDto, @MappingTarget Payment payment);


    PaymentResDto toResDto(Payment payment);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment partialUpdate(PaymentResDto paymentResDto, @MappingTarget Payment payment);
}