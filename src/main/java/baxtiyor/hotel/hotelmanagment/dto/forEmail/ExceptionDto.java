package baxtiyor.hotel.hotelmanagment.dto.forEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDto {
    private Integer status;
    private String message;
}
