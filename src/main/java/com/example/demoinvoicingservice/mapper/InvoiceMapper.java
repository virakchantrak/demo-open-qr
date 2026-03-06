package com.example.demoinvoicingservice.mapper;

import com.example.demoinvoicingservice.common.mapper.AbstractMapper;
import com.example.demoinvoicingservice.dto.InvoiceDTO;
import com.example.demoinvoicingservice.dto.KhQrCallbackDTO;
import com.example.demoinvoicingservice.dto.ManualPaymentDTO;
import com.example.demoinvoicingservice.entity.InvoiceEntity;
import com.example.demoinvoicingservice.web.vo.request.ManualPaymentRequestVO;
import com.example.demoinvoicingservice.web.vo.response.InvoiceDetailResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper extends AbstractMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);


    @Mapping(target = "gatewaySetting.id", source = "gatewaySetting.gatewaySettingId")
    InvoiceDTO fromEntityToDTO(InvoiceEntity entity);

    void fromDTOToResponseVO(InvoiceDTO dTO, @MappingTarget InvoiceDetailResponseVO portalResponseVO);

    @Mapping(source = "amount", target = "totalAmount")
    void fromManualPaymentRequestTODTO(ManualPaymentRequestVO requestVO, @MappingTarget ManualPaymentDTO dto);

    @Mapping(source = "bankAccount", target = "account")
    @Mapping(source = "accountName", target = "customerName")
    void fromManualDTOToKhQrCallback(ManualPaymentDTO dto, @MappingTarget KhQrCallbackDTO khQrCallbackDTO);
}
