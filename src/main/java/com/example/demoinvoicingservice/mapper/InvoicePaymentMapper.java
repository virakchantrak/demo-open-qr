package com.example.demoinvoicingservice.mapper;

import com.example.demoinvoicingservice.dto.InvoicePaymentDTO;
import com.example.demoinvoicingservice.dto.KhQrCallbackDTO;
import com.example.demoinvoicingservice.entity.InvoiceEntity;
import com.example.demoinvoicingservice.entity.InvoicePaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoicePaymentMapper {
    InvoicePaymentMapper INSTANCE = Mappers.getMapper(InvoicePaymentMapper.class);

    void toDto(InvoicePaymentEntity entity, @MappingTarget InvoicePaymentDTO dTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoiceId", source = "invoiceEntity.id")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    @Mapping(target = "bankName", source = "khQr.bankName")
    @Mapping(target = "transactionId", source = "khQr.transactionId")
    @Mapping(target = "requestTransactionId", source = "originalTransactionId")
    @Mapping(target = "bankAccount", source = "khQr.bankAccount")
    @Mapping(target = "bankAccountName", source = "khQr.bankAccountName")
    @Mapping(target = "hashId", source = "khQr.hashId")
    @Mapping(target = "receiveAmount",
            expression = "java(invoiceEntity.getTotal() != null ? invoiceEntity.getTotal() : java.math.BigDecimal.ZERO)")
    @Mapping(
            target = "changeAmount",
            expression = "java(khQr.getChangeAmount() == null ? java.math.BigDecimal.ZERO : khQr.getChangeAmount())"
    )
    InvoicePaymentEntity toEntity(
            InvoiceEntity invoiceEntity,
            KhQrCallbackDTO khQr,
            String paymentMethod,
            String originalTransactionId
    );

}