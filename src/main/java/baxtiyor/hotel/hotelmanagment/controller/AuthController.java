package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.forEmail.*;
import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.service.AuthService;
import baxtiyor.hotel.hotelmanagment.service.UserService;
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
    private final UserService userService;

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

    @PostMapping("forgotPassword")
    public HttpEntity<?> forgotPassword(@RequestParam String email){
        String forgotToken = authService.forgotPassword(email);
        return ResponseEntity.ok("Forgot "+forgotToken);
    }

    @PostMapping("recover")
    public HttpEntity<?> forgotMailCode(@RequestBody CoderRequestDto code, HttpServletRequest request){
        System.out.println("Code: " + code.getCode());
        TokenDto token = authService.confirmMailcodeAndRemovePassword(code.getCode(), request);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }

    @PostMapping("changePassword")
    public HttpEntity<?> changePassword(@RequestParam String password){
        return ResponseEntity.ok(userService.changePassword(password));
    }

}
