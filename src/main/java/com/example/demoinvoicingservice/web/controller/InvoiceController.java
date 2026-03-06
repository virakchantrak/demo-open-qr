package com.example.demoinvoicingservice.web.controller;

import com.example.demoinvoicingservice.dto.GetInvoiceDetailDTO;
import com.example.demoinvoicingservice.dto.InvoiceDTO;
import com.example.demoinvoicingservice.dto.ManualPaymentDTO;
import com.example.demoinvoicingservice.mapper.InvoiceMapper;
import com.example.demoinvoicingservice.service.InvoiceService;
import com.example.demoinvoicingservice.web.vo.request.ManualPaymentRequestVO;
import com.example.demoinvoicingservice.web.vo.response.InvoiceDetailResponseVO;
import jakarta.validation.Valid;
import kh.com.wingbank.framework.khemarak.core.exception.BusinessException;
import kh.com.wingbank.framework.khemarak.web.vo.response.ResponseMessage;
import kh.com.wingbank.framework.khemarak.web.vo.response.ResponseMessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/find-by-id/{id}")
    public ResponseMessage<InvoiceDetailResponseVO> findById(@PathVariable String id, @RequestParam(required = false, defaultValue = "false") boolean ignoreMerchantId) throws BusinessException {
        GetInvoiceDetailDTO requestDTO = new GetInvoiceDetailDTO();
        requestDTO.setId(id);
        requestDTO.setIgnoreMerchantId(ignoreMerchantId);
        InvoiceDTO invoiceDTO = invoiceService.findByInvoiceId(requestDTO);
        InvoiceDetailResponseVO responseVO = new InvoiceDetailResponseVO();
        InvoiceMapper.INSTANCE.fromDTOToResponseVO(invoiceDTO, responseVO);
        return new ResponseMessageBuilder<InvoiceDetailResponseVO>().addData(responseVO).success().build();
    }

    @GetMapping("find-by-order-reference-no/{id}")
    public ResponseMessage<InvoiceDetailResponseVO> findByOrderReference(@PathVariable String id) throws BusinessException {
        InvoiceDTO invoiceDTO = invoiceService.findByOrderReferenceNo(id);
        InvoiceDetailResponseVO responseVO = new InvoiceDetailResponseVO();
        InvoiceMapper.INSTANCE.fromDTOToResponseVO(invoiceDTO, responseVO);
        return new ResponseMessageBuilder<InvoiceDetailResponseVO>().addData(responseVO).success().build();
    }

    @PostMapping("cancel-by-id/{id}")
    public ResponseMessage<Valid> cancelById(@PathVariable String id) throws BusinessException {
        invoiceService.cancelByInvoiceId(id);
        return new ResponseMessageBuilder<Valid>().success().build();
    }

    @PostMapping("cancel-by-order-reference-no/{orderReferenceNo}")
    public ResponseMessage<Valid> cancelByOrderReference(@PathVariable String orderReferenceNo) throws BusinessException {
        invoiceService.cancelByOrderReferenceNo(orderReferenceNo);
        return new ResponseMessageBuilder<Valid>().success().build();
    }

    @PostMapping("manual-payment/invoice-id/{invoiceId}")
    public ResponseMessage<Valid> manualPaymentByMerchantId(@Valid @RequestBody ManualPaymentRequestVO requestVO, @PathVariable String invoiceId) throws BusinessException {
        ManualPaymentDTO manualPaymentDTO = new ManualPaymentDTO();
        InvoiceMapper.INSTANCE.fromManualPaymentRequestTODTO(requestVO, manualPaymentDTO);
        manualPaymentDTO.setId(invoiceId);
        invoiceService.updateManualPayment(manualPaymentDTO);
        return new ResponseMessageBuilder<Valid>().success().build();
    }

    @PostMapping("manual-payment/order-reference-no/{orderReferenceNo}")
    public ResponseMessage<Valid> manualPaymentByOrderReferenceNo(@Valid @RequestBody ManualPaymentRequestVO requestVO, @PathVariable String orderReferenceNo) throws BusinessException {
        ManualPaymentDTO manualPaymentDTO = new ManualPaymentDTO();
        InvoiceMapper.INSTANCE.fromManualPaymentRequestTODTO(requestVO, manualPaymentDTO);
        manualPaymentDTO.setOrderReferenceNo(orderReferenceNo);
        invoiceService.updateManualPayment(manualPaymentDTO);
        return new ResponseMessageBuilder<Valid>().success().build();
    }

}
