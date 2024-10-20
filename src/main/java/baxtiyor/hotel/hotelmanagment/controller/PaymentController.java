package baxtiyor.hotel.hotelmanagment.controller;

import baxtiyor.hotel.hotelmanagment.dto.req.PaymentReqDto;
import baxtiyor.hotel.hotelmanagment.dto.res.PaymentResDto;
import baxtiyor.hotel.hotelmanagment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payment")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5")int size){
        Page<PaymentResDto> payments = paymentService.getAll(page, size);
        return ResponseEntity.ok(payments);
    }

    @PostMapping("add")
    public ResponseEntity<?> addPayment(PaymentReqDto reqDto){
        return ResponseEntity.ok(paymentService.addPayment(reqDto));
    }

    @PutMapping("edit")
    public ResponseEntity<?> editPayment(@PathVariable UUID paymentId,PaymentReqDto reqDto){
        return ResponseEntity.ok(paymentService.editPayment(paymentId,reqDto));
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deletePayment(@PathVariable UUID id){
        return ResponseEntity.ok(paymentService.delete(id));
    }


}
