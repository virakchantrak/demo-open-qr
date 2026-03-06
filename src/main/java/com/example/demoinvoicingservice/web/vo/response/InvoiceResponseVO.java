package com.example.demoinvoicingservice.web.vo.response;

import lombok.Data;

@Data
public class InvoiceResponseVO {
	private String qrCodeUrl;
	private String qrCodeContent;
	private String orderReferenceNo;
	private String invoiceId;
}