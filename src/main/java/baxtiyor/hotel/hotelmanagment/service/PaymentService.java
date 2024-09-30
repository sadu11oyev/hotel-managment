package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.PaymentResDto;

import java.util.List;

public interface PaymentService {
    Object addPayment(PaymentReqDto reqDto);

    List<PaymentResDto> getAll();
}
