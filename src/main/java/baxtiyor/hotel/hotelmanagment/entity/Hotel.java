package baxtiyor.hotel.hotelmanagment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel extends BaseEntity {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Boolean isDelete;
}