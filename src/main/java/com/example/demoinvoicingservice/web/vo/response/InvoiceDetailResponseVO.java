package com.example.demoinvoicingservice.web.vo.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceDetailResponseVO {
    private String id;
    private String orderReferenceNo;
    private String currency;
    private String subTotal;
    private String discount;
    private String vat;
    private String serviceCharge;
    private String total;
    private String deliveryFee;
    private Date issueDate;
    private Date dueDate;
    private Date paidDate;
    private String tax;
    private String billingFrom;
    private String billingTo;
    private String paidStatus;
    private String taxInvoiceNumber;
    private String cmlInvoiceNumber;
    private String genInvoiceNumber;
    private String qrCodeUrl;
    private CustomerResponseVO customer;
    private MerchantResponseVO merchant;
    private GatewaySettingVO gatewaySetting;
    private List<InvoiceItemResponseVO> invoiceItems;
    private List<InvoicePaymentResponseVO> invoicePayments;
    private String invoiceStatus;
    private List<String> orderReferences;
    private String description;
    
}
