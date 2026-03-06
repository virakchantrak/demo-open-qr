package com.example.demoinvoicingservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInvoiceDetailDTO {
	private String id;
	private String orderReferenceNo;
	private boolean ignoreMerchantId;
}