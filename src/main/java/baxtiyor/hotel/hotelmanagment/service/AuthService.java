package baxtiyor.hotel.hotelmanagment.service;

import baxtiyor.hotel.hotelmanagment.dto.forEmail.ReqDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.ReqInfoDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.TokenDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface AuthService {
    TokenDto login(ReqDto loginDto);

    String register(ReqDto reqDto);

    TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request);

    UUID addInfos(ReqInfoDto reqInfoDto);
}
