package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.req.DownloadOrderDto;
import baxtiyor.hotel.hotelmanagment.dto.req.OrderReqDto;
import baxtiyor.hotel.hotelmanagment.dto.req.RateDto;
import baxtiyor.hotel.hotelmanagment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public HttpEntity<?> makeOrder(@RequestBody OrderReqDto orderReqDto){
        return orderService.makeOrder(orderReqDto);
    }

    @PostMapping("rate/{id}")
    public HttpEntity<?> rateOrder(@PathVariable UUID id, @RequestBody RateDto rateDto){
        return orderService.rateOrder(id,rateDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("download")
    public HttpEntity<?> downloadOrders(@RequestBody DownloadOrderDto downloadOrderDto){
        return orderService.downloadOrderFile(downloadOrderDto);
    }


}
