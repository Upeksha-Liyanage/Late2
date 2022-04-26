package com.service.payment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.payment.model.InvoicePayment;
import com.service.payment.repo.InvPaymentRepo;

@RestController
@RequestMapping("/invoice")
public class InvPaymentController {

	@Autowired
	InvPaymentRepo invPaymentRepo;
	
	@PostMapping("/inv-save")
	public ResponseEntity<InvoicePayment> createPayment(@RequestBody InvoicePayment payment){
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
			String formattedDate = sdf.format(date);
			payment.setInvDate(formattedDate);
			InvoicePayment payment2=invPaymentRepo.save(payment);
			return new ResponseEntity<>(payment2,HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/inv-find")
	 public List<InvoicePayment> getAllPayments(){
		 return invPaymentRepo.findAll();
	 }
	 
	 @GetMapping("/inv-payment/{id}")
	  public ResponseEntity<InvoicePayment> getPaymentById(@PathVariable("id") Integer id) {
	    Optional<InvoicePayment> paymentData = invPaymentRepo.findById(id);
	    if (paymentData.isPresent()) {
	      return new ResponseEntity<>(paymentData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @DeleteMapping("/del-inv/{id}")
	  public ResponseEntity<String> deletePay(@PathVariable("id") Integer id) {
	    try {
	      invPaymentRepo.deleteById(id);
	      return new ResponseEntity<>("Deleted !",HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PutMapping("/update-inv/{id}")
	  public ResponseEntity<InvoicePayment> updatePay(@PathVariable("id") Integer id, @RequestBody InvoicePayment payment) {
	    Optional<InvoicePayment> payData = invPaymentRepo.findById(id);
	    if (payData.isPresent()) {
	    	InvoicePayment payment2=invPaymentRepo.save(payment);
			return new ResponseEntity<>(payment2,HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
