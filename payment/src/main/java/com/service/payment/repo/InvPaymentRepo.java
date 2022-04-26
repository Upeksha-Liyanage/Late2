package com.service.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.payment.model.InvoicePayment;

public interface InvPaymentRepo extends JpaRepository<InvoicePayment, Integer>{

}
