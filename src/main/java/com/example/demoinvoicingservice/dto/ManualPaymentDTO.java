package com.example.demoinvoicingservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManualPaymentDTO {
    private String id;
    private String orderReferenceNo;
    private String amount;
    private String totalAmount;
    private String bankAccount;
    private String accountName;
    private String bankName;
    private String changeAmount;
    private String paymentMethod;
    private String currency;
    private List<String> orderReferences;
}
