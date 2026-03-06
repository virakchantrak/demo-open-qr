package com.example.demoinvoicingservice.web.vo.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceItemResponseVO {
    private String id;
    private String productId;
    private String product;
    private BigDecimal productQty;
    private BigDecimal productUnitPrice;
    private String currency;
    private String amount;
    private String uom;
}
