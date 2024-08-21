package com.books.dto;

import com.books.entities.PaymentMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

    private long payment_id;
    private Long orderId;
    private Long userId;
    private String paymentStatus;
    private PaymentMode mode;
}

