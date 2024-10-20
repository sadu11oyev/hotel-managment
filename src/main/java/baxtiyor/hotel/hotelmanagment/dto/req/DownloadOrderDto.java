package baxtiyor.hotel.hotelmanagment.dto.req;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DownloadOrderDto {
    LocalDate from;
    LocalDate to;
}
