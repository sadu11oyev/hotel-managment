package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.req.HotelReqDto;
import baxtiyor.hotel.hotelmanagment.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;
    @GetMapping
    public ResponseEntity<?> getHotels(){
        return ResponseEntity.ok(hotelService.getHotels());
    }

    @PostMapping
    public ResponseEntity<?> addHotel(HotelReqDto reqDto){
        return ResponseEntity.ok(hotelService.addHotel(reqDto));
    }

}
