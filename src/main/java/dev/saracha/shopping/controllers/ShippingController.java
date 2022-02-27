package dev.saracha.shopping.controllers;

import dev.saracha.shopping.domains.Shipping;
import dev.saracha.shopping.services.ShippingService;
import dtos.ShippingRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    @PostMapping("/shipment")
    public ResponseEntity<Shipping> makeShipment(@RequestBody ShippingRequestDTO shippingRequestDTO
            , @RequestParam("customerId") Long customerId
            , @RequestParam("orderId") Long orderId) {

        Shipping shipping = shippingService.makeShipment(shippingRequestDTO, customerId, orderId);
        return ResponseEntity.ok().body(shipping);
    }
}