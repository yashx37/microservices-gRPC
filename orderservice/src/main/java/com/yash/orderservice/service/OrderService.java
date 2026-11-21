package com.yash.orderservice.service;

import com.yash.orderservice.grpc.PaymentGrpcClient;
import com.yash.payment.grpc.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentGrpcClient paymentGrpcClient;


    public OrderService(PaymentGrpcClient paymentGrpcClient) {
        this.paymentGrpcClient = paymentGrpcClient;
    }

    public PaymentResponse createOrder(String orderId, double amount){
        return paymentGrpcClient.processPayment(orderId, amount);
    }
}
