package baxtiyor.hotel.hotelmanagment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;

    private Double totalPrice;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @CreationTimestamp
    private LocalDateTime createdAt;

}