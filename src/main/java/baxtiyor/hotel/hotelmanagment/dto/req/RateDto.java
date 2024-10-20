package baxtiyor.hotel.hotelmanagment.dto.req;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Order}
 */
@Value
public class RateDto implements Serializable {
    Integer rate;
    String comment;
}