package baxtiyor.hotel.hotelmanagment.dto.forEmail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqDto {
    private String email;
    private String password;
}
