package baxtiyor.hotel.hotelmanagment.service;


import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.UserResDto;

import java.util.UUID;

public interface UserService {
    UserResDto getUser();

    UserResDto editUser(UserReqDto reqDto);

    UserResDto changePassword(String password);
}
