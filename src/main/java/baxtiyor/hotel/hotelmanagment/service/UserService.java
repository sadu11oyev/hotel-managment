package baxtiyor.hotel.hotelmanagment.service;


import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.UserResDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

public interface UserService {
    UserResDto getUser();

    UserResDto editUser(UserReqDto reqDto);

    UserResDto changePassword(String password);

    HttpEntity<?> getAllCustomers(Pageable pageable);

    HttpEntity<?> getAllAdmins(Pageable pageable);

    HttpEntity<?> getMyOrders(Pageable pageable);
}
