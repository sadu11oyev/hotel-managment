package baxtiyor.hotel.hotelmanagment.dto.res;

import baxtiyor.hotel.hotelmanagment.entity.enums.PaymentType;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Payment}
 */
@Value
public class PaymentResDto implements Serializable {
    UUID id;
    UUID orderId;
    UUID userId;
    Double amount;
    PaymentType paymentType;
    LocalDateTime paymentDate;
}