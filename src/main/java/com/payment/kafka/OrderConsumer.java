package com.payment.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.model.Order;
import com.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderConsumer {

    @Autowired
    private PaymentProducer paymentProducer;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "orders", groupId = "payment-group-RAVI-FINAL")
    public void consume(String message) {
        try {
            System.out.println("========================================");
            System.out.println(">>> [80-PAYMENT] RAW MESSAGE: " + message);

            Order order = objectMapper.readValue(message, Order.class);
            System.out.println(">>> [80-PAYMENT] CONSUMED Event: Order{id='" + order.getId() + "', product='" + order.getProduct() + "', qty=" + order.getQty() + "}");

            // Simulate payment processing
            Payment payment = new Payment();
            payment.setPaymentId(UUID.randomUUID().toString().substring(0, 8));
            payment.setOrderId(order.getId());
            payment.setStatus("SUCCESS");
            payment.setAmount(order.getQty() * 100.0);

            System.out.println("========================================");
            System.out.println("********** PAYMENT PROCESSED **********");
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Order ID: " + payment.getOrderId());
            System.out.println("Amount: $" + payment.getAmount());
            System.out.println("Status: " + payment.getStatus());
            System.out.println("***************************************");
            System.out.println("========================================");

            // Publish to payments topic - Interlinked with next service
            paymentProducer.sendPayment(payment);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}