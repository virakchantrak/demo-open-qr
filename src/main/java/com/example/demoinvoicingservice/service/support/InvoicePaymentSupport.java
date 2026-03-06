package com.example.demoinvoicingservice.service.support;

import com.example.demoinvoicingservice.common.consts.InvoiceStatus;
import com.example.demoinvoicingservice.common.consts.PaidStatus;
import com.example.demoinvoicingservice.common.mapper.KhQrCallbackMapper;
import com.example.demoinvoicingservice.dto.InvoiceDTO;
import com.example.demoinvoicingservice.dto.InvoicePaymentDTO;
import com.example.demoinvoicingservice.dto.KhQrCallbackDTO;
import com.example.demoinvoicingservice.entity.InvoiceEntity;
import com.example.demoinvoicingservice.entity.InvoicePaymentEntity;
import com.example.demoinvoicingservice.mapper.InvoicePaymentMapper;
import com.example.demoinvoicingservice.repository.InvoiceRepository;
import com.example.demoinvoicingservice.utils.StringModifiedUtils;
import com.example.demoinvoicingservice.web.vo.request.KhQrCallbackRequestVO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Predicate;
import kh.com.wingbank.framework.khemarak.core.exception.BusinessException;
import kh.com.wingbank.framework.khemarak.core.util.CollectionUtils;
import kh.com.wingbank.framework.khemarak.core.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InvoicePaymentSupport {
    private final InvoiceRepository invoiceRepository;
    private final InvoicePaymentMapper invoicePaymentMapper;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public List<InvoicePaymentDTO> getInvoicePayment(InvoiceEntity invoiceEntity) {
        if (invoiceEntity == null || invoiceEntity.getInvoicePayments() == null || invoiceEntity.getInvoicePayments().isEmpty()) {
            return Collections.emptyList();
        }

        List<InvoicePaymentDTO> invoicePayments = new ArrayList<>();

        for (InvoicePaymentEntity invoicePayment : invoiceEntity.getInvoicePayments()) {
            if (invoicePayment.getPaymentJson() == null) continue;

            InvoicePaymentDTO dto = new InvoicePaymentDTO();
            InvoicePaymentMapper.INSTANCE.toDto(invoicePayment, dto);

            extractPaymentFieldsFromJson(invoicePayment, dto);

            dto.setPaidDate(invoiceEntity.getPaidDate());
            dto.setHashId(StringModifiedUtils.getShortHash(dto.getHashId()));
            invoicePayments.add(dto);
        }

        return invoicePayments;
    }

    private static void extractPaymentFieldsFromJson(InvoicePaymentEntity invoicePayment, InvoicePaymentDTO dto) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(invoicePayment.getPaymentJson());

            // Currency
            JsonNode currencyNode = jsonNode.get("currency");
            dto.setCurrency(currencyNode != null ? currencyNode.asText() : StringUtils.EMPTY);

            // Fee
            JsonNode feeNode = jsonNode.get("fee");
            if (feeNode != null) {
                try {
                    dto.setFee(new BigDecimal(feeNode.asText()));
                } catch (NumberFormatException e) {
                    log.warn("Invalid fee '{}' for invoicePaymentId {}",
                            feeNode.asText(), invoicePayment.getId());
                }
            }

        } catch (JsonProcessingException e) {
            log.error("Failed to parse payment JSON for invoicePaymentId {}",
                    invoicePayment.getId(), e);
        }
    }

    public void saveInvoicePayment(
            InvoiceDTO invoice,
            KhQrCallbackDTO khQrCallback,
            String paymentMethod,
            String transactionId,
            String originalTransactionId
    ) throws BusinessException {
        List<String> orderReferences = new ArrayList<>();
        if (!CollectionUtils.isEmpty(invoice.getOrderReferences())) {
            orderReferences.addAll(invoice.getOrderReferences());
        }
        orderReferences.add(invoice.getOrderReferenceNo());

        List<InvoiceEntity> invoiceEntities = this.getListInvoiceByOrderReference(orderReferences, invoice.getMerchantId());
        if (CollectionUtils.isEmpty(invoiceEntities)) return;

        for (InvoiceEntity invoiceEntity : invoiceEntities) {
            if (InvoiceStatus.CANCEL.name().equalsIgnoreCase(invoiceEntity.getInvoiceStatus())) continue;

            invoiceEntity.setPaidDate(new Date());
            invoiceEntity.setPaidCurrency(khQrCallback.getCurrency());
            invoiceEntity.setPaidStatus(String.valueOf(PaidStatus.PAID));

            InvoicePaymentEntity invoicePaymentEntity = invoicePaymentMapper.toEntity(
                    invoiceEntity,
                    khQrCallback,
                    paymentMethod,
                    originalTransactionId
            );

            try {
                KhQrCallbackRequestVO paymentJson = KhQrCallbackMapper.INSTANCE.from(khQrCallback);
                paymentJson.setOrderReferenceNo(khQrCallback.getId());
                paymentJson.setTransactionId(originalTransactionId);
                invoicePaymentEntity.setPaymentJson(OBJECT_MAPPER.writeValueAsString(paymentJson));
            } catch (JsonProcessingException e) {
                throw new BusinessException("Failed to serialize payment JSON", String.valueOf(e));
            }
            invoiceEntity.getInvoicePayments().add(invoicePaymentEntity);
        }

        this.invoiceRepository.saveAll(invoiceEntities);
        //TODO: Alert telegram notification
        //if (!StringUtils.isBlank(transactionId))
        //    paymentNotify(invoice, khQrCallback);
    }

    public List<InvoiceEntity> getListInvoiceByOrderReference(
            List<String> orderReferenceNos,
            String merchantId
    ) {

        Specification<InvoiceEntity> spec = (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (!CollectionUtils.isEmpty(orderReferenceNos)) {
                predicates.add(root.get("orderReferenceNo").in(orderReferenceNos));
            }

            if (StringUtils.hasText(merchantId)) {
                predicates.add(cb.equal(root.get("merchantId"), merchantId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return invoiceRepository.findAll(spec);
    }
}
