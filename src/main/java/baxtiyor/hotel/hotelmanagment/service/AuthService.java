package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.forEmail.ReqDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.TokenDto;
import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface AuthService {
    TokenDto login(ReqDto loginDto);

    String register(ReqDto reqDto);

    TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request);

    UUID addInfos(UserReqDto reqDto);

    String forgotPassword(String email);

    TokenDto confirmMailcodeAndRemovePassword(Integer code, HttpServletRequest request);
}
