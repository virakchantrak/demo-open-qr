package com.example.demoinvoicingservice.web.vo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatewaySettingVO {
	private String id;
	private String thirdPartyId;
	private Boolean isRequireCrc;
	private Boolean isRequireTransaction;
	private Boolean allowDoubleTransaction;
	private Boolean isInternalService;
}