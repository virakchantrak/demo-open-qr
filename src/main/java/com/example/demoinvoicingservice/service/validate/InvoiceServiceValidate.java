package com.example.demoinvoicingservice.service.validate;

import com.example.demoinvoicingservice.common.consts.ErrorCode;
import com.example.demoinvoicingservice.common.consts.InvoiceStatus;
import com.example.demoinvoicingservice.common.consts.ManualPaymentMethod;
import com.example.demoinvoicingservice.common.consts.PaidStatus;
import com.example.demoinvoicingservice.dto.ManualPaymentDTO;
import com.example.demoinvoicingservice.entity.InvoiceEntity;
import kh.com.wingbank.framework.khemarak.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class InvoiceServiceValidate {

    public void validateUpdateInvoice(InvoiceEntity invoiceEntity) throws BusinessException {
        if (ObjectUtils.isEmpty(invoiceEntity)) {
            throw new BusinessException(ErrorCode.U0001.name(), ErrorCode.U0001.getDesc());
        }
        if (ObjectUtils.isNotEmpty(invoiceEntity.getDueDate()) && invoiceEntity.getDueDate().before(new Date())) {
            throw new BusinessException(ErrorCode.U0007.name(), ErrorCode.U0007.getDesc());
        } else if (String.valueOf(InvoiceStatus.CANCEL).equalsIgnoreCase(invoiceEntity.getInvoiceStatus())) {
            throw new BusinessException(ErrorCode.U0006.name(), ErrorCode.U0006.getDesc());
        }
        if (Boolean.FALSE.equals(invoiceEntity.getGatewaySetting().getAllowDoubleTransaction())
                && String.valueOf(PaidStatus.PAID).equalsIgnoreCase(invoiceEntity.getPaidStatus())) {
            throw new BusinessException(ErrorCode.U0005.name(), ErrorCode.U0005.getDesc());
        }
    }

    public void validateManualPayment(ManualPaymentDTO manualPaymentDTO, InvoiceEntity invoiceEntity) throws BusinessException {
        ManualPaymentMethod manualPaymentMethod = ManualPaymentMethod.valueOf(manualPaymentDTO.getPaymentMethod());
        BigDecimal manualAmount = new BigDecimal(manualPaymentDTO.getAmount());
        if ((manualPaymentMethod.equals(ManualPaymentMethod.CHECK) || manualPaymentMethod.equals(ManualPaymentMethod.CARD) || manualPaymentMethod.equals(ManualPaymentMethod.BANK_TRANSFER))
                && StringUtils.isAnyBlank(manualPaymentDTO.getAccountName(), manualPaymentDTO.getBankAccount(), manualPaymentDTO.getBankName())
        ) {
            throw new BusinessException(ErrorCode.U444.getCode(), "Some fields is missing.");
        } else if (invoiceEntity.getTotal().compareTo(manualAmount) != 0) {
            throw new BusinessException(ErrorCode.U444.getCode(), "Payment amount should be the same as invoice amount.");
        } else if (!invoiceEntity.getCurrency().equalsIgnoreCase(manualPaymentDTO.getCurrency())) {
            throw new BusinessException(ErrorCode.U444.getCode(), "Payment currency should be the same as invoice currency.");
        }
    }
}
