package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.config.AuditorAware;
import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.PaymentResDto;
import baxtiyor.hotel.hotelmanagment.entity.Payment;
import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoleName;
import baxtiyor.hotel.hotelmanagment.mapper.PaymentMapper;
import baxtiyor.hotel.hotelmanagment.repo.OrderRepository;
import baxtiyor.hotel.hotelmanagment.repo.PaymentRepository;
import baxtiyor.hotel.hotelmanagment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final AuditorAware auditorAware;
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final OrderRepository orderRepository;

    @Override
    public PaymentResDto addPayment(PaymentReqDto reqDto) {
        Payment payment = mapper.toEntity(reqDto);
        payment.setPaymentDate(LocalDateTime.now());
        repository.save(payment);
        return mapper.toResDto(payment);
    }

    @Override
    public Page<PaymentResDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        User user = auditorAware.getAuthenticatedUser();
        List<Payment> payments;
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN));
        if (isAdmin){
            payments = repository.findAll();
        }else {
            payments = repository.findUserPayments(user.getId());
        }
        List<PaymentResDto> paymentResDtos = payments.stream().map(mapper::toResDto).toList();
        return new PageImpl<>(paymentResDtos, pageable, paymentResDtos.size());
    }

    @Override
    public String delete(UUID id) {
        Payment payment = repository.findById(id).get();
        repository.delete(payment);
        return "Payment deleted!";
    }

    @Override
    public PaymentResDto editPayment(UUID paymentId, PaymentReqDto reqDto) {
        Payment payment = repository.findById(paymentId).get();
        payment.setAmount(reqDto.getAmount());
        payment.setPaymentType(reqDto.getPaymentType());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setOrder(orderRepository.findById(reqDto.getOrderId()).get());
        repository.save(payment);
        return mapper.toResDto(payment);
    }
}
