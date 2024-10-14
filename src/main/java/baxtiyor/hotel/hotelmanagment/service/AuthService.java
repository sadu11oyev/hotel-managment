package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.forEmail.ReqDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.TokenDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    TokenDto login(ReqDto loginDto);

    String register(ReqDto reqDto);

    TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request);
}
