package com.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.dto.PaymentDTO;
import com.books.entities.Payment;
import com.books.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<List<PaymentDTO>> getAllPayments(){
		List<PaymentDTO> paydto = paymentService.getAllPayments();
		return ResponseEntity.ok(paydto);
	}
}
