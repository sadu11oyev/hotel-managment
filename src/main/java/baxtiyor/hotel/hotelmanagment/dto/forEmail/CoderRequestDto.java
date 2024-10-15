package baxtiyor.hotel.hotelmanagment.dto.forEmail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoderRequestDto{
    private Integer code;
}
