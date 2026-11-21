package com.yash.orderservice.controller;

import com.yash.orderservice.dto.PaymentResponseDto;
import com.yash.orderservice.grpc.PaymentGrpcClient;
import com.yash.payment.grpc.PaymentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final PaymentGrpcClient paymentGrpcClient;

    public OrderController(PaymentGrpcClient paymentGrpcClient){
        this.paymentGrpcClient = paymentGrpcClient;
    }

    @PostMapping("/pay")
    public PaymentResponseDto makePayment(
            @RequestParam String orderId,
            @RequestParam double amount
    ){
        PaymentResponse response = paymentGrpcClient.processPayment(orderId, amount);

        return new PaymentResponseDto(
                response.getPaymentId(),
                response.getStatus(),
                response.getMessage()
        );
    }
}
