package baxtiyor.hotel.hotelmanagment.entity;

import baxtiyor.hotel.hotelmanagment.entity.enums.RoomStatus;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room extends BaseEntity {
    private Integer roomNumber;
    private Integer floor;
    private Double price;
    private Boolean isDelete;

    @ManyToOne
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    private RoomType typeRoom;

    @Enumerated(EnumType.STRING)
    private RoomStatus statusRoom;

}