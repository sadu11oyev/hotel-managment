package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.forEmail.CodeRequestDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.ReqDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.ResponseDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.TokenDto;
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
    public HttpEntity<?> confirmMailCode(@RequestBody CodeRequestDto codeDto, HttpServletRequest request){
        TokenDto token = authService.confirmMailCodeAndRegister(codeDto.getCode(), request);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }
}
