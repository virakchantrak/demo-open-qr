package com.example.demoinvoicingservice.web.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoicePaymentResponseVO implements Serializable {
	private static final long serialVersionUID = -2776238272044002121L;
	
	private String id;
    private String bankAccount;
    private String bankAccountName;
    private String bankName;
    private String currency;
    private String paymentMethod;

    private Date paidDate;
    private BigDecimal fee;
    private String transactionId;
    private String hashId;
}
