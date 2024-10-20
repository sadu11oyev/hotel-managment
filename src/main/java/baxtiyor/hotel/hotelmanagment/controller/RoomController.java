package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.req.RoomReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.RoomResDto;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import baxtiyor.hotel.hotelmanagment.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/room")
@PreAuthorize("hasAnyRole('ADMIN')")
public class RoomController {
    private final RoomService roomService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("{hotelId}")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5")int size,
                                      @PathVariable UUID hotelId){
        Page<RoomResDto> rooms = roomService.getRoomsByHotelId(page,size,hotelId);
        return ResponseEntity.ok(rooms);
    }
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("{hotelId}/{type}")
    public ResponseEntity<?> getAllByType(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5")int size,
                                    @PathVariable UUID hotelId,@RequestParam RoomType type ){
        Page<RoomResDto> rooms = roomService.getRoomsByHotelIdAndType(page,size,hotelId,type);
        return ResponseEntity.ok(rooms);
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
