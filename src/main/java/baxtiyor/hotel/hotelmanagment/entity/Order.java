package baxtiyor.hotel.hotelmanagment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private User customer;

    private Double totalPrice;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}