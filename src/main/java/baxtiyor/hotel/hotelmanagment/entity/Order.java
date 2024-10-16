package baxtiyor.hotel.hotelmanagment.entity;

import baxtiyor.hotel.hotelmanagment.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;

    private Double totalPrice;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}