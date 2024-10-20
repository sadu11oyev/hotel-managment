package baxtiyor.hotel.hotelmanagment.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String confirm=request.getHeader("Confirm");
        String forgot=request.getHeader("Forgot");
        if (authorization != null && authorization.startsWith("Bearer ")
                || !(request.getRequestURI().contains("auth") || request.getRequestURI().contains("/swagger") || request.getRequestURI().contains("/v3")))
        {
            assert authorization != null;
            String token=authorization.substring(7);
            if (jwtUtil.isValid(token)){
                List<SimpleGrantedAuthority> roles = jwtUtil.getRoles(token);
                String email = jwtUtil.getEmail(token);
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, roles);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        else if (confirm!=null&&confirm.startsWith("Confirm ")) {
            String token=confirm.substring(8);
            if (jwtUtil.isValid(token)){
                String email=jwtUtil.getEmail(token);
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        else if (forgot!=null&&forgot.startsWith("Forgot ")) {
            String token=forgot.substring(7);
            if (jwtUtil.isValid(token)){
                String email=jwtUtil.currentEmail(token);
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
