package baxtiyor.hotel.hotelmanagment.component;

import baxtiyor.hotel.hotelmanagment.entity.*;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoleName;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoomType;
import baxtiyor.hotel.hotelmanagment.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final OrderRepository orderRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args)  {
        if (ddl.equals("create")){
            Role roleAdmin=new Role(1, RoleName.ROLE_ADMIN);
            Role roleUser=new Role(2,RoleName.ROLE_USER);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            User admin=User.builder()
                    .email("acer@gmail.com")
                    .firstName("ACER")
                    .lastName("ACER")
                    .phoneNumber("+998999999999")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleAdmin))
                    .build();
            User user=User.builder()
                    .email("inter@gmail.com")
                    .firstName("INTEL")
                    .lastName("INTER")
                    .phoneNumber("+998999999999")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleUser))
                    .build();
            userRepository.save(admin);
            userRepository.save(user);

            Hotel hotel=Hotel.builder()
                    .email("Hotel@gmail.com")
                    .address("Tashkent")
                    .phoneNumber("+998999999999")
                    .name("Oqshom")
                    .build();
            hotelRepository.save(hotel);

            Room room1= Room.builder()
                    .floor(1)
                    .hotel(hotel)
                    .price(2000d)
                    .roomNumber(1)
                    .typeRoom(RoomType.STANDARD)
                    .build();

            Room room2= Room.builder()
                    .floor(1)
                    .hotel(hotel)
                    .price(2000d)
                    .roomNumber(2)
                    .typeRoom(RoomType.STANDARD)
                    .build();

            roomRepository.save(room1);
            roomRepository.save(room2);

            Order order1= Order.builder()
                    .room(room1)
                    .user(user)
                    .checkIn(LocalDate.of(2024,10,20))
                    .checkOut(LocalDate.of(2024,10,23))
                    .build();

            Order order2= Order.builder()
                    .room(room1)
                    .user(user)
                    .checkIn(LocalDate.of(2024,10,26))
                    .checkOut(LocalDate.of(2024,10,28))
                    .build();

            orderRepository.save(order1);
            orderRepository.save(order2);
        }
    }
}
