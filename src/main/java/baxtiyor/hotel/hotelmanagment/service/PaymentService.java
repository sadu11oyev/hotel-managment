package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.PaymentResDto;
import org.springframework.data.domain.Page;

import java.util.UUID;


public interface PaymentService {
    Object addPayment(PaymentReqDto reqDto);

    Page<PaymentResDto> getAll(int page, int size);

    String delete(UUID id);

    PaymentResDto editPayment(UUID paymentId, PaymentReqDto reqDto);
}
