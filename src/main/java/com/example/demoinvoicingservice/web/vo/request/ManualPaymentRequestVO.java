package com.example.demoinvoicingservice.web.vo.request;

import com.example.demoinvoicingservice.common.consts.CurrencyType;
import com.example.demoinvoicingservice.common.consts.ManualPaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ManualPaymentRequestVO {
    @NotNull(message = "can't be null")
    private String amount;
    private String bankAccount;
    private String accountName;
    private String bankName;
    private String changeAmount;
    @NotNull(message = "can't be null")
    private ManualPaymentMethod paymentMethod;
    @NotNull(message = "can't be null")
    private CurrencyType currency;
}
