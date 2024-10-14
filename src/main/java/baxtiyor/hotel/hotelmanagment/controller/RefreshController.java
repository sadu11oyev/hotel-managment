package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.service.RefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/refresh")
public class RefreshController {

    private final RefreshService refreshService;

    @GetMapping
    public HttpEntity<?> refresh(){
        String token = refreshService.refresh();
        return ResponseEntity.ok(token);
    }
}
