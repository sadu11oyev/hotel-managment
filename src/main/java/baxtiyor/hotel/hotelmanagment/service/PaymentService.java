package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.PaymentResDto;
import org.springframework.data.domain.Page;


public interface PaymentService {
    Object addPayment(PaymentReqDto reqDto);

    Page<PaymentResDto> getAll(int page, int size);
}
