package com.example.demoinvoicingservice.dto;

import com.example.demoinvoicingservice.common.consts.InvoiceType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class InvoiceDTO {
    private String id;
    private String orderReferenceNo;
    private String gatewaySettingId;
    private String customerId;
    private String merchantId;
    private String platform;
    private String invoiceStatus;
    private String currency;
    private BigDecimal subTotal;
    private BigDecimal discount;
    private BigDecimal vat;
    private BigDecimal serviceCharge;
    private BigDecimal tax;
    private BigDecimal total;
    private BigDecimal deliveryFee;
    private InvoiceType type;
    private Date issueDate;
    private Date invalidationDate;
    private Date dueDate;
    private String billingFrom;
    private String billingTo;
    private String taxInvoiceNumber;
    private String cmlInvoiceNumber;
    private String genInvoiceNumber;
    private String paidStatus;
    private String paidCurrency;
    private String qrCodeUrl;
    private String qrCodeContent;
    private Date paidDate;
    private String keywords;
    private CustomerDTO customer;
    private MerchantDTO merchant;
    private GatewaySettingDTO gatewaySetting;
    private List<InvoiceItemDTO> invoiceItems;
    private List<InvoicePaymentDTO> invoicePayments;
    ////if header invoicing_require_crc is true
    private String crc;
    private List<String> orderReferences;
    private String description;
}