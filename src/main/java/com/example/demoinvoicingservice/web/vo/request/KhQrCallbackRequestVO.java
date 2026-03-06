package com.example.demoinvoicingservice.web.vo.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KhQrCallbackRequestVO {
    private String transactionDate;
    private String transactionId;
    private String amount;
    private String account;
    private String billerName;
    private String fee;
    private String feeCurrency;
    private String totalAmount;
    private String psTrnId;
    private String fcTrnId;
    private String currency;
    private String customerName;
    @NotNull
    private String orderReferenceNo;
    private String bankName;
    private String bankAccount;
    private String bankAccountName;
    private String hashId;
    private String message;
    private String referenceNo;
    private String remark;
    private String status;
    private String type;
}
