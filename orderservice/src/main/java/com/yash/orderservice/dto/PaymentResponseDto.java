package com.yash.orderservice.dto;

public record PaymentResponseDto(
    String paymentId,
    String status,
    String message
){}
