package com.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @GetMapping
    public String health() {
        return "80-payment-service RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic!";
    }
}