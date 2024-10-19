package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.config.AuditorAware;
import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.UserResDto;
import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.mapper.UserMapper;
import baxtiyor.hotel.hotelmanagment.repo.UserRepository;
import baxtiyor.hotel.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final AuditorAware auditorAware;
    private final UserMapper userMapper;

    @Override
    public UserResDto getUser() {
        User user = auditorAware.getAuthenticatedUser();
        return userMapper.toResDto(user);
    }

    @Override
    public UserResDto editUser(UserReqDto reqDto) {
        User user = auditorAware.getAuthenticatedUser();
        user.setFirstName(reqDto.getFirstName());
        user.setLastName(reqDto.getLastName());
        user.setPhoneNumber(reqDto.getPhoneNumber());
        return userMapper.toResDto(user);
    }

}
