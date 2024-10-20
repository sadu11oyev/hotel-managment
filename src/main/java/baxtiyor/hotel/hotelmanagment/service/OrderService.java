package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.req.DownloadOrderDto;
import baxtiyor.hotel.hotelmanagment.dto.req.OrderReqDto;
import baxtiyor.hotel.hotelmanagment.dto.req.RateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface OrderService {
    HttpEntity<?> getAllOrders(Pageable pageable);

    HttpEntity<?> makeOrder(OrderReqDto orderReqDto);

    HttpEntity<?> rateOrder(UUID id, RateDto rateDto);

    HttpEntity<?> downloadOrderFile(DownloadOrderDto downloadOrderDto);
}
