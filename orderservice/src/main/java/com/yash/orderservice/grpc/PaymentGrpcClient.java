package com.yash.orderservice.grpc;

import com.yash.payment.grpc.PaymentRequest;
import com.yash.payment.grpc.PaymentResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import com.yash.payment.grpc.PaymentServiceGrpc;

import jakarta.annotation.PreDestroy;

@Component
public class PaymentGrpcClient {

    private final ManagedChannel channel;
    private final PaymentServiceGrpc.PaymentServiceBlockingStub stub;

    public PaymentGrpcClient(){
        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        this.stub = PaymentServiceGrpc.newBlockingStub(channel);
    }

    public PaymentResponse processPayment(String orderId, double amount){
        PaymentRequest request = PaymentRequest.newBuilder()
                .setOrderId(orderId)
                .setAmount(amount)
                .build();

        return stub.processPayment(request);
    }

    @PreDestroy
    public void shutdown(){
        channel.shutdown();
    }
}
