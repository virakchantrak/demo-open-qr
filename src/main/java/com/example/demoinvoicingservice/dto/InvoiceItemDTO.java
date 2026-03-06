package com.example.demoinvoicingservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceItemDTO {
    private String id;
    private String invoiceId;
    private String productId;
    private String product;
    private BigDecimal productQty;
    private BigDecimal productUnitPrice;
    private String currency;
    private BigDecimal amount;
    private String uom;
}