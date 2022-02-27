package dev.saracha.shopping.controllers;

import dev.saracha.shopping.domains.Payment;
import dev.saracha.shopping.services.PaymentService;
import dev.saracha.shopping.dtos.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO, @RequestParam("orderId") Long orderId) {
        Payment newPayment = paymentService.makePayment(paymentRequestDTO, orderId);
        return ResponseEntity.ok().body(newPayment);
    }
}