package com.payment.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendPayment(Payment payment) {
        try {
            String json = objectMapper.writeValueAsString(payment);
            kafkaTemplate.send("payments", json);
            System.out.println(">>> [80-PAYMENT] PRODUCED Payment: PaymentId=" + payment.getPaymentId() + " OrderId=" + payment.getOrderId() + " Status=" + payment.getStatus() + " Amount=$" + payment.getAmount());
            System.out.println(">>> JSON: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}