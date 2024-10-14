package baxtiyor.hotel.hotelmanagment.entity;

import baxtiyor.hotel.hotelmanagment.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
    @OneToOne
    private Order order;

    @ManyToOne
    private User user;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private LocalDateTime paymentDate;

}