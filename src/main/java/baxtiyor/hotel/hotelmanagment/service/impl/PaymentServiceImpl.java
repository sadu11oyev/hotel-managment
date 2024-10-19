package baxtiyor.hotel.hotelmanagment.service.impl;

import baxtiyor.hotel.hotelmanagment.config.AuditorAware;
import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.PaymentResDto;
import baxtiyor.hotel.hotelmanagment.entity.Payment;
import baxtiyor.hotel.hotelmanagment.entity.User;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoleName;
import baxtiyor.hotel.hotelmanagment.mapper.PaymentMapper;
import baxtiyor.hotel.hotelmanagment.repo.PaymentRepository;
import baxtiyor.hotel.hotelmanagment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final AuditorAware auditorAware;
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    public PaymentResDto addPayment(PaymentReqDto reqDto) {
        Payment payment = mapper.toEntity(reqDto);
        payment.setPaymentDate(LocalDateTime.now());
        repository.save(payment);
        return mapper.toResDto(payment);
    }

    @Override
    public List<PaymentResDto> getAll() {
        User user = auditorAware.getAuthenticatedUser();
        List<Payment> payments = new ArrayList<>();
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN));
        if (isAdmin){
            payments = repository.findAll();
        }else {
            payments = repository.findUserPayments(user.getId());
        }
        return payments.stream().map(mapper::toResDto).toList();
    }
}
