package baxtiyor.hotel.hotelmanagment.security;

import baxtiyor.hotel.hotelmanagment.dto.forEmail.ReqDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtUtil {
    public String generateToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getKey())
                .compact();
    }
    public String generateRefreshToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*14))
                .signWith(getKey())
                .compact();
    }
    public String generateConfirmToken(ReqDto reqDto, Integer code) {
        return Jwts.builder()
                .claim("mailCode",code)
                .claim("email",reqDto.getEmail())
                .claim("password",reqDto.getPassword())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getKey())
                .compact();
    }
    public String generateForgotToken(String email, Integer code) {
        return Jwts.builder()
                .claim("mailCode",code)
                .claim("email",email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        String secretKey = "1234567891234567891234567891234567891234567891234567891234567891";
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmail(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Integer getMailCode(String confirm){
        Claims claims = getClaims(confirm);
        return claims.get("mailCode",Integer.class);
    }

    public ReqDto getReqDto(String confirm){
        Claims claims = getClaims(confirm);
        return new ReqDto(claims.get("email", String.class),claims.get("password", String.class));
    }
    public String currentEmail(String recoverToken) {
        Claims claims = getClaims(recoverToken);
        return  claims.get("email", String.class);
    }

    public List<SimpleGrantedAuthority> getRoles(String token) {
        Claims claims = getClaims(token);
        String str = claims.get("roles", String.class);
        String[] arr= str.split(",");
        System.out.println(str);
        System.out.println(claims);
        return Arrays.stream(arr).map(SimpleGrantedAuthority::new).toList();

    }
}
