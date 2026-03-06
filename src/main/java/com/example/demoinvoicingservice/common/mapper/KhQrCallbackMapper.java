package com.example.demoinvoicingservice.common.mapper;

import com.example.demoinvoicingservice.dto.KhQrCallbackDTO;
import com.example.demoinvoicingservice.web.vo.request.KhQrCallbackRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KhQrCallbackMapper {
    KhQrCallbackMapper INSTANCE = Mappers.getMapper(KhQrCallbackMapper.class);

    KhQrCallbackRequestVO from(KhQrCallbackDTO requestDTO);
}
