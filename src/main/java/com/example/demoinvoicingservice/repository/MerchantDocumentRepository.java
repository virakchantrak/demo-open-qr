package com.example.demoinvoicingservice.repository;

import com.example.demoinvoicingservice.entity.MerchantDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantDocumentRepository
        extends JpaRepository<MerchantDocumentEntity, String> {

    Optional<MerchantDocumentEntity>
    findFirstByEntityIdAndEntityTypeAndDocumentTypeAndStatus(
            String entityId,
            String entityType,
            String documentType,
            String status
    );
}

