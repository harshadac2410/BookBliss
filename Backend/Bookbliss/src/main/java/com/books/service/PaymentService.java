package com.books.service;

import java.util.List;

import com.books.dto.PaymentDTO;
import com.books.entities.Payment;

public interface PaymentService {

	List<PaymentDTO> getAllPayments();
}
