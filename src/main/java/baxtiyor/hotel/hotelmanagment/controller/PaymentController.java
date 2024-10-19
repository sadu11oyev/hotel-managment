package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payment")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(paymentService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> addPayment(PaymentReqDto reqDto){
        return ResponseEntity.ok(paymentService.addPayment(reqDto));
    }


}
