package com.example.demoinvoicingservice.service.support;

import com.example.demoinvoicingservice.common.consts.StatusType;
import com.example.demoinvoicingservice.dto.InvoiceDTO;
import com.example.demoinvoicingservice.entity.MerchantDocumentEntity;
import com.example.demoinvoicingservice.repository.MerchantDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvoiceMerchantSupport {
    private final MerchantDocumentRepository merchantDocumentRepository;
    @Value("${app.file-prefix-domain}")
    private String filePrefixDomain;

    public void setMerchantLogo(InvoiceDTO invoiceDTO) {

        if (invoiceDTO.getMerchant() == null) {
            return;
        }

        Optional<MerchantDocumentEntity> merchantDocumentOpt =
                merchantDocumentRepository
                        .findFirstByEntityIdAndEntityTypeAndDocumentTypeAndStatus(
                                invoiceDTO.getMerchantId(),
                                "company",
                                "PROFILE",
                                StatusType.ACTIVE.name()
                        );

        merchantDocumentOpt.ifPresent(merchantDocument -> invoiceDTO.getMerchant()
                .setLogo(filePrefixDomain + merchantDocument.getUrl()));
    }

}
