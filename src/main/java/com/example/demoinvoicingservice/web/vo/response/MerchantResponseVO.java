package com.example.demoinvoicingservice.web.vo.response;

import lombok.Data;

@Data
public class MerchantResponseVO {
    private String id;
    private String businessName;
    private String settlementAccount;
    private String logo;
}
