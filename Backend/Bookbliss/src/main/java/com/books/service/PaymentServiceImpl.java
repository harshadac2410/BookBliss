package com.books.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.dto.PaymentDTO;
import com.books.entities.Payment;
import com.books.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public List<PaymentDTO> getAllPayments() {
		List<Payment> pay =  paymentRepository.findAll();
		return pay.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	
	 private PaymentDTO convertToDto(Payment payment) {
	        PaymentDTO dto = new PaymentDTO();
	        dto.setPayment_id(payment.getPayment_id());
	        if (payment.getOrder() != null) {
	            dto.setOrderId(payment.getOrder().getOrder_id());
	        }
	        
	        if (payment.getUser() != null) {
	            dto.setUserId(payment.getUser().getUser_id());
	        }
	        dto.setPaymentStatus(payment.getPaymentStatus());
	        dto.setMode(payment.getMode());
	        return dto;
	    }

}
