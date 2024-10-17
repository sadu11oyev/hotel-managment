package baxtiyor.hotel.hotelmanagment.config;

import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.repo.UserRepository;
import baxtiyor.hotel.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditorAware {
    private final UserRepository userRepository;

    public User getAuthenticatedUser(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(email);
    }
}
