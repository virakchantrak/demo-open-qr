package com.example.demoinvoicingservice.repository;

import com.example.demoinvoicingservice.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String>, JpaSpecificationExecutor<InvoiceEntity> {
}
