package com.example.demoinvoicingservice.web.vo.response;

import lombok.Data;

@Data
public class KhQrCallbackResponseVO {
    private String transactionDate;
    private String transactionId;
    private String amount;
    private String customerId;
    private String customerName;
    private String orderReferenceNo;
}
