package com.yash.paymentservice.grpc;

import com.yash.payment.grpc.PaymentRequest;
import com.yash.payment.grpc.PaymentResponse;
import com.yash.payment.grpc.PaymentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class PaymentGrpcService extends PaymentServiceGrpc.PaymentServiceImplBase{

    @Override
    public void processPayment(
            PaymentRequest request,
            StreamObserver<PaymentResponse> responseObserver
    ){
        System.out.println("received payment request");
        System.out.println("Order id: " + request.getOrderId());
        System.out.println("amount: " + request.getAmount());

        PaymentResponse response = PaymentResponse.newBuilder()
                .setPaymentId("p101")
                .setStatus("SUCCESS")
                .setMessage("payment processed successfully")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
