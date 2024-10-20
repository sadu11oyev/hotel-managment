package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.component.CurrentUser;
import baxtiyor.hotel.hotelmanagment.config.AuditorAware;
import baxtiyor.hotel.hotelmanagment.dto.req.UserReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.OrderResDto;
import baxtiyor.hotel.hotelmanagment.dto.res.UserResDto;
import baxtiyor.hotel.hotelmanagment.entity.Order;
import baxtiyor.hotel.hotelmanagment.entity.Role;
import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoleName;
import baxtiyor.hotel.hotelmanagment.mapper.OrderMapper;
import baxtiyor.hotel.hotelmanagment.mapper.UserMapper;
import baxtiyor.hotel.hotelmanagment.repo.OrderRepository;
import baxtiyor.hotel.hotelmanagment.repo.RoleRepository;
import baxtiyor.hotel.hotelmanagment.repo.UserRepository;
import baxtiyor.hotel.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final AuditorAware auditorAware;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
        repository.save(user);
        return userMapper.toResDto(user);
    }

    @Override
    public UserResDto changePassword(String password) {
       User user = auditorAware.getAuthenticatedUser();
       user.setPassword(passwordEncoder.encode(password));
       repository.save(user);
        return userMapper.toResDto(user);
    }

    @Override
    public HttpEntity<?> getAllCustomers(Pageable pageable) {
        List<User> allCustomers = repository.findAllCustomers();
        List<UserResDto> collect = allCustomers.stream().map(userMapper::toResDto).toList();
        Page<UserResDto> pagedCustomers=new PageImpl<>(collect,pageable,collect.size());
        return ResponseEntity.ok(pagedCustomers);
    }

    @Override
    public HttpEntity<?> getAllAdmins(Pageable pageable) {
        List<User> allAdmins = repository.findAllAdmins();
        List<UserResDto> collect = allAdmins.stream().map(userMapper::toResDto).toList();
        Page<UserResDto> pagedAdmins=new PageImpl<>(collect,pageable,collect.size());
        return ResponseEntity.ok(pagedAdmins);
    }

    @Override
    public HttpEntity<?> getMyOrders(Pageable pageable) {
        String email = (String) currentUser.getMe();
        User customer = repository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Order> customerOrders = orderRepository.findAllByUser(customer);
        List<OrderResDto> orderResDtoList = customerOrders.stream().map(orderMapper::toDto1).collect(Collectors.toList());
        Page<OrderResDto> pagedOrders=new PageImpl<>(orderResDtoList,pageable,orderResDtoList.size());
        return ResponseEntity.ok(pagedOrders);
    }

}
