package baxtiyor.hotel.hotelmanagment.dto.req;

import baxtiyor.hotel.hotelmanagment.entity.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link baxtiyor.hotel.hotelmanagment.entity.Payment}
 */
@Value
public class PaymentReqDto implements Serializable {
    UUID orderId;
    @NotNull
    Double amount;
    @NotNull
    PaymentType paymentType;
}