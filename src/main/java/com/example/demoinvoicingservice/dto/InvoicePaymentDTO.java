package com.example.demoinvoicingservice.dto;

import com.example.demoinvoicingservice.common.consts.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoicePaymentDTO {
	private String id;
	private String invoiceId;
	private String bankAccount;
	private String bankName;
	private BigDecimal changeAmount;
	private BigDecimal receiveAmount;
	private Date paidDate;
	private BigDecimal fee;
	private String transactionId;
	private PaymentMethod paymentMethod;
	private String bankAccountName;
	private String currency;
	private String hashId;
}