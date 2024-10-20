package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.service.OrderService;
import baxtiyor.hotel.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final OrderService orderService;
    private final UserService userService;
    @GetMapping("orders")
    public HttpEntity<?> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "") int size){
        Pageable pageable= PageRequest.of(page,size);
        return orderService.getAllOrders(pageable);
    }

    @GetMapping("customers")
    public HttpEntity<?> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size){
        Pageable pageable= PageRequest.of(page,size);
        return userService.getAllCustomers(pageable);
    }

    @GetMapping("admins")
    public HttpEntity<?> getAllAdmins(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size){
        Pageable pageable= PageRequest.of(page,size);
        return userService.getAllAdmins(pageable);
    }
}
