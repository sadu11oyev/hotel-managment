package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.repo.OrderRepository;
import baxtiyor.hotel.hotelmanagment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

}
