package com.example.demoinvoicingservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MerchantDTO implements Serializable {
	private static final long serialVersionUID = 7270633009073191188L;
	
	private String id;
    private String businessName;
    private String settlementAccount;
    private String logo;
}
