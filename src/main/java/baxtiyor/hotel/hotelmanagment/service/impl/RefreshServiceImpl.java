package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.security.CustomUserDetailsService;
import baxtiyor.hotel.hotelmanagment.security.JwtUtil;
import baxtiyor.hotel.hotelmanagment.service.RefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    public String refresh() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return "Bearer "+jwtUtil.generateToken(userDetails);
    }
}
