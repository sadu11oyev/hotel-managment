package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.req.HotelReqDto;
import baxtiyor.hotel.hotelmanagment.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping("{id}")
    public ResponseEntity<?> editHotel(@PathVariable UUID id, HotelReqDto reqDto){
        return ResponseEntity.ok(hotelService.editHotel(id,reqDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable UUID id){
        return ResponseEntity.ok(hotelService.deleteHotel(id));
    }

}
