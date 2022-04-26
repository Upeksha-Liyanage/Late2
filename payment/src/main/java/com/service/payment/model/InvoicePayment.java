package com.service.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inv_payment")
public class InvoicePayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String invDate;
	private String customerId;
	private String payType;
	private Double amount;
	private String cardNo;
	private String cvv;
	private String cardExpireDate;
	
	public InvoicePayment() {
	
		// TODO Auto-generated constructor stub
	}

	public InvoicePayment(int id, String invDate, String customerId, String payType, Double amount, String cardNo,
			String cvv, String cardExpireDate) {
		super();
		this.id = id;
		this.invDate = invDate;
		this.customerId = customerId;
		this.payType = payType;
		this.amount = amount;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.cardExpireDate = cardExpireDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardExpireDate() {
		return cardExpireDate;
	}

	public void setCardExpireDate(String cardExpireDate) {
		this.cardExpireDate = cardExpireDate;
	}
	
	
}
