package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.component.CurrentUser;
import baxtiyor.hotel.hotelmanagment.dto.req.DownloadOrderDto;
import baxtiyor.hotel.hotelmanagment.dto.req.OrderReqDto;
import baxtiyor.hotel.hotelmanagment.dto.req.RateDto;
import baxtiyor.hotel.hotelmanagment.dto.res.OrderResDto;
import baxtiyor.hotel.hotelmanagment.entity.Order;
import baxtiyor.hotel.hotelmanagment.entity.Room;
import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.mapper.OrderMapper;
import baxtiyor.hotel.hotelmanagment.repo.OrderRepository;
import baxtiyor.hotel.hotelmanagment.repo.RoomRepository;
import baxtiyor.hotel.hotelmanagment.repo.UserRepository;
import baxtiyor.hotel.hotelmanagment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    @Override
    public HttpEntity<?> getAllOrders(Pageable pageable) {
        List<Order> all = orderRepository.findAll();
        List<OrderResDto> collect = all.stream().map(orderMapper::toDto1).toList();
        Page<OrderResDto> pagedOrder=new PageImpl<>(collect,pageable,collect.size());
        return ResponseEntity.ok(pagedOrder);
    }

    @Override
    public HttpEntity<?> makeOrder(OrderReqDto orderReqDto) {
        List<Room> rooms = roomRepository.allEmptyRooms(orderReqDto.getRoomTypeRoom(), orderReqDto.getCheckIn(), orderReqDto.getCheckOut());
        if (!rooms.isEmpty()){
            String email = (String)currentUser.getMe();
            User user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            Room selectedRoom = rooms.get(0);
            Order order=Order.builder()
                    .user(user)
                    .room(selectedRoom)
                    .checkIn(orderReqDto.getCheckIn())
                    .checkOut(orderReqDto.getCheckOut())
                    .build();
            orderRepository.save(order);
            return ResponseEntity.ok("Room is booked. Your room is "+selectedRoom.getRoomNumber()+" on "+ selectedRoom.getFloor()+" floor");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("Sorry, On this date we have no free rooms with this credentials");
        }
    }

    @Override
    public HttpEntity<?> rateOrder(UUID id, RateDto rateDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setRate(rateDto.getRate());
        order.setComment(rateDto.getComment());
        orderRepository.save(order);
        return ResponseEntity.ok("Thank you for your feedback");
    }

    @Override
    public HttpEntity<?> downloadOrderFile(DownloadOrderDto downloadOrderDto) {
        List<Order> orders = orderRepository.findAllByCreatedAtBetween(downloadOrderDto.getFrom().atStartOfDay(), downloadOrderDto.getTo().atTime(LocalTime.MAX));
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Orders");

            // Создаем заголовок
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Room");
            header.createCell(2).setCellValue("Customer Name");
            header.createCell(3).setCellValue("Check in");
            header.createCell(4).setCellValue("Check out");
            header.createCell(5).setCellValue("Order Date");
            header.createCell(6).setCellValue("Rate");
            header.createCell(7).setCellValue("Comment");
            header.createCell(8).setCellValue("Total Amount");

            // Заполняем данными
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(order.getId().toString());
                row.createCell(1).setCellValue("Room :"+order.getRoom().getRoomNumber()+" Floor :"+order.getRoom().getFloor());
                row.createCell(2).setCellValue(order.getUser().getEmail());
                row.createCell(3).setCellValue(order.getCheckIn());
                row.createCell(4).setCellValue(order.getCheckOut());
                row.createCell(5).setCellValue(order.getCreatedAt());
                row.createCell(6).setCellValue(order.getRate()==null?"Not rated":order.getRate().toString());
                row.createCell(7).setCellValue(order.getComment()==null?"Has no comment":order.getComment());
                row.createCell(8).setCellValue(ChronoUnit.DAYS.between(order.getCheckIn(),order.getCheckOut())*order.getRoom().getPrice());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            byte[] excelBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=orders.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
