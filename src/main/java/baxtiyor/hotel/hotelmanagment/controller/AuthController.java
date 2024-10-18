package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.config.AuditorAware;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.*;
import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody ReqDto reqDto){
        TokenDto token = authService.login(reqDto);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }


    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody ReqDto reqDto) {
        String confirmToken = authService.register(reqDto);
        return ResponseEntity.ok("Confirm "+confirmToken);
    }

    @PostMapping("confirm")
    public HttpEntity<?> confirmMailCode(@RequestBody CoderRequestDto code, HttpServletRequest request){
        System.out.println("Code: " + code.getCode());
        TokenDto token = authService.confirmMailCodeAndRegister(code.getCode(), request);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }

    @PostMapping("addInfos")
    public HttpEntity<?> addFields(@RequestBody UserReqDto userReqDto){
        return ResponseEntity.ok(authService.addInfos(userReqDto));
    }
}
