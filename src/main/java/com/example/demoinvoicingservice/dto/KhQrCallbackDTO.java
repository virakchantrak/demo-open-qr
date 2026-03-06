package com.example.demoinvoicingservice.dto;

import com.example.demoinvoicingservice.web.vo.request.KhQrCallbackRequestVO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KhQrCallbackDTO extends KhQrCallbackRequestVO {
    private String id;
    private String customerId;
    private BigDecimal changeAmount;
}
