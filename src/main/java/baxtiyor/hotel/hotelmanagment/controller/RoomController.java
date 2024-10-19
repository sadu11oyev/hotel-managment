package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;
import baxtiyor.hotel.hotelmanagment.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("{hotelId}")
    public ResponseEntity<?> getRooms(@PathVariable UUID hotelId){
        return ResponseEntity.ok(roomService.getRoomsByHotelId(hotelId));
    }

    @PostMapping("add")
    public ResponseEntity<?> addRoom(RoomReqDto reqDto){
        return ResponseEntity.ok(roomService.addRoom(reqDto));
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> editRoom(@PathVariable UUID id, RoomReqDto reqDto){
        return ResponseEntity.ok(roomService.editRoom(id,reqDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable UUID id){
        return ResponseEntity.ok(roomService.deleteRoom(id));
    }
}
