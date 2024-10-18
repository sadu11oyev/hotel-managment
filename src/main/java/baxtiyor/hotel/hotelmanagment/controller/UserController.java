package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.config.AuditorAware;
import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(userService.getUser());
    }
    @PutMapping
    public ResponseEntity<?> editUser(UserReqDto reqDto){
        return ResponseEntity.ok(userService.editUser(reqDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
