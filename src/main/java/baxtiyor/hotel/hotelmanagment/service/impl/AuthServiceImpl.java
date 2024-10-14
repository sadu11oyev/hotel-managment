package baxtiyor.hotel.hotelmanagment.service.impl;
import baxtiyor.hotel.hotelmanagment.component.MailCodeSender;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.ReqDto;
import baxtiyor.hotel.hotelmanagment.dto.forEmail.TokenDto;
import baxtiyor.hotel.hotelmanagment.entity.Role;
import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoleName;
import baxtiyor.hotel.hotelmanagment.repo.RoleRepository;
import baxtiyor.hotel.hotelmanagment.repo.UserRepository;
import baxtiyor.hotel.hotelmanagment.security.JwtUtil;
import baxtiyor.hotel.hotelmanagment.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MailCodeSender mailCodeSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public TokenDto login(ReqDto loginDto) {
        var auth = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(auth);
        UserDetails userDetails=(UserDetails)authenticate.getPrincipal();
        return new TokenDto(
                "Bearer "+jwtUtil.generateToken(userDetails),
                "Bearer "+jwtUtil.generateRefreshToken(userDetails)
        );
    }

    @Override
    public String register(ReqDto reqDto) {
        Integer code=new Random().nextInt(1000,10000);
        mailCodeSender.sendMessage(code,reqDto.getEmail());
        return jwtUtil.generateConfirmToken(reqDto,code);
    }

    @Override
    public TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request) {
        String confirmToken = request.getHeader("Confirm");
        Integer mailCode = jwtUtil.getMailCode(confirmToken);
        ReqDto reqDto = jwtUtil.getReqDto(confirmToken);
        if (code.equals(mailCode)){
            Role userRole = roleRepository.findRoleByRoleName(RoleName.ROLE_USER);
            User user=User.builder()
                    .roles(List.of(userRole))
                    .email(reqDto.getEmail())
                    .password(passwordEncoder.encode(reqDto.getPassword()))
                    .build();
            userRepository.save(user);
            return new TokenDto(
                    "Bearer "+jwtUtil.generateToken(user),
                    "Bearer "+jwtUtil.generateRefreshToken(user)
            );
        }
        throw new BadCredentialsException("Invalid code try again");
    }
}


