package com.example.demoinvoicingservice.service;

import com.example.demoinvoicingservice.dto.GetInvoiceDetailDTO;
import com.example.demoinvoicingservice.dto.InvoiceDTO;
import com.example.demoinvoicingservice.dto.ManualPaymentDTO;
import kh.com.wingbank.framework.khemarak.core.exception.BusinessException;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

public interface InvoiceService {

    InvoiceDTO findByInvoiceId(GetInvoiceDetailDTO requestDTO) throws BusinessException;

    InvoiceDTO findByOrderReferenceNo(String orderReferenceNo) throws BusinessException;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void cancelByInvoiceId(String id) throws BusinessException;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void cancelByOrderReferenceNo(String orderReferenceNo) throws BusinessException;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void updateManualPayment(ManualPaymentDTO manualPaymentDTO) throws BusinessException;
}
