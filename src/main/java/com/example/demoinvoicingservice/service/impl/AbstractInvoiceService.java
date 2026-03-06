package com.example.demoinvoicingservice.service.impl;

import com.example.demoinvoicingservice.common.consts.ErrorCode;
import com.example.demoinvoicingservice.common.consts.InvoiceStatus;
import com.example.demoinvoicingservice.common.consts.PaidStatus;
import com.example.demoinvoicingservice.dto.GetInvoiceDetailDTO;
import com.example.demoinvoicingservice.dto.InvoiceDTO;
import com.example.demoinvoicingservice.dto.InvoicePaymentDTO;
import com.example.demoinvoicingservice.dto.KhQrCallbackDTO;
import com.example.demoinvoicingservice.dto.ManualPaymentDTO;
import com.example.demoinvoicingservice.entity.InvoiceEntity;
import com.example.demoinvoicingservice.mapper.InvoiceMapper;
import com.example.demoinvoicingservice.repository.InvoiceRepository;
import com.example.demoinvoicingservice.service.InvoiceService;
import com.example.demoinvoicingservice.service.support.InvoiceMerchantSupport;
import com.example.demoinvoicingservice.service.support.InvoicePaymentSupport;
import com.example.demoinvoicingservice.service.validate.InvoiceServiceValidate;
import com.example.demoinvoicingservice.utils.RequestHeaderUtils;
import jakarta.persistence.criteria.Predicate;
import kh.com.wingbank.framework.khemarak.core.exception.BusinessException;
import kh.com.wingbank.framework.khemarak.core.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AbstractInvoiceService implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoicePaymentSupport invoicePaymentSupport;
    private final InvoiceMerchantSupport merchantSupport;
    private final InvoiceServiceValidate invoiceServiceValidate;

    @Override
    @Transactional(readOnly = true)
    public InvoiceDTO findByInvoiceId(GetInvoiceDetailDTO requestDTO) throws BusinessException {
        return this.findOne(requestDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceDTO findByOrderReferenceNo(String orderReferenceNo) throws BusinessException {
        GetInvoiceDetailDTO requestDTO = new GetInvoiceDetailDTO();
        requestDTO.setOrderReferenceNo(orderReferenceNo);
        requestDTO.setIgnoreMerchantId(false);
        return this.findOne(requestDTO);
    }

    @Override
    public void cancelByInvoiceId(String id) throws BusinessException {
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setId(id);
        this.cancel(invoice);
    }

    @Override
    public void cancelByOrderReferenceNo(String orderReferenceNo) throws BusinessException {
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setOrderReferenceNo(orderReferenceNo);
        this.cancel(invoice);
    }

    @Override
    public void updateManualPayment(ManualPaymentDTO manualPaymentDTO) throws BusinessException {
        InvoiceEntity invoiceEntity = getInvoiceByIdOrReference(
                manualPaymentDTO.getId(),
                manualPaymentDTO.getOrderReferenceNo(),
                false
        );

        InvoiceDTO invoiceDTO = InvoiceMapper.INSTANCE.fromEntityToDTO(invoiceEntity);
        invoiceServiceValidate.validateManualPayment(manualPaymentDTO, invoiceEntity);
        invoiceServiceValidate.validateUpdateInvoice(invoiceEntity);
        KhQrCallbackDTO khQrCallback = new KhQrCallbackDTO();
        InvoiceMapper.INSTANCE.fromManualDTOToKhQrCallback(manualPaymentDTO, khQrCallback);
        khQrCallback.setCustomerId(invoiceEntity.getCustomerId());
        this.invoicePaymentSupport.saveInvoicePayment(
                invoiceDTO,
                khQrCallback,
                manualPaymentDTO.getPaymentMethod(),
                null,
                null
        );
        invoiceEntity.setPaidDate(new Date());
        invoiceEntity.setPaidCurrency(khQrCallback.getCurrency());
        invoiceEntity.setPaidStatus(String.valueOf(PaidStatus.PAID));
        this.invoiceRepository.save(invoiceEntity);
    }

    /**
     * Find one invoice by id or orderReferenceNo
     */
    private InvoiceDTO findOne(GetInvoiceDetailDTO invoice) throws BusinessException {
        InvoiceEntity invoiceEntity = getInvoiceByIdOrReference(
                invoice.getId(), invoice.getOrderReferenceNo(),
                invoice.isIgnoreMerchantId()
        );

        InvoiceDTO invoiceDTO = InvoiceMapper.INSTANCE.fromEntityToDTO(invoiceEntity);
        List<InvoicePaymentDTO> invoicePayments = this.invoicePaymentSupport.getInvoicePayment(invoiceEntity);
        invoiceDTO.setInvoicePayments(invoicePayments);
        merchantSupport.setMerchantLogo(invoiceDTO);
        return invoiceDTO;
    }

    /**
     * Cancel Invoice by id or orderReferenceNo
     */
    private void cancel(InvoiceEntity invoice) throws BusinessException {

        InvoiceEntity invoiceEntity = getInvoiceByIdOrReference(
                invoice.getId(),
                invoice.getOrderReferenceNo(),
                false
        );

        validateCancelable(invoiceEntity);

        invoiceEntity.setInvoiceStatus(InvoiceStatus.CANCEL.name());
        invoiceEntity.setCancelledDate(new Date());
        invoiceEntity.setCancelledBy(RequestHeaderUtils.getUserId());

        invoiceRepository.save(invoiceEntity);
    }

    /**
     * Validate Cancel Invoice
     */
    private static void validateCancelable(InvoiceEntity invoiceEntity) throws BusinessException {
        if (PaidStatus.PAID.name().equals(invoiceEntity.getPaidStatus())) {
            throw new BusinessException(ErrorCode.U0003.name(), ErrorCode.U0003.getDesc());
        }

        if (InvoiceStatus.CANCEL.name().equalsIgnoreCase(invoiceEntity.getInvoiceStatus())) {
            throw new BusinessException(ErrorCode.U0004.name(), ErrorCode.U0004.getDesc());
        }
    }

    /**
     * Find invoice by:
     * id or orderReferenceNo and merchantId
     */
    private InvoiceEntity getInvoiceByIdOrReference(
            String id,
            String orderReferenceNo,
            boolean ignoreMerchantId
    ) throws BusinessException {

        Specification<InvoiceEntity> spec = (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(id)) {
                predicates.add(cb.equal(root.get("id"), id));
            } else if (StringUtils.hasText(orderReferenceNo)) {
                predicates.add(cb.equal(root.get("orderReferenceNo"), orderReferenceNo));
            }

            if (!ignoreMerchantId) {
                predicates.add(cb.equal(root.get("merchantId"),
                        RequestHeaderUtils.getMerchantId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return invoiceRepository.findAll(spec)
                .stream().findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.U0001.name(), ErrorCode.U0001.getDesc()));
    }

}

