package com.example.demoinvoicingservice.dto;

import lombok.Data;

@Data
public class GatewaySettingDTO {
	private String id;
	private String thirdPartyId;
	private String callbackUrl;
	private String payload;
	private String grantType;
	private Boolean isRequireCrc;
	private Boolean isRequireTransaction;
	private Boolean allowDoubleTransaction;
	private Boolean isInternalService;
	private String crcSalt;
	private String callbackAuthType;
	private String callbackAuthData;
}