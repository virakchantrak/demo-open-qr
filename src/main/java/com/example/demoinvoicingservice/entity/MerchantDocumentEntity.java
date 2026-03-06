package com.example.demoinvoicingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = MerchantDocumentEntity.TABLE_NAME)
public class MerchantDocumentEntity implements RecyclableEntity<String> {
    public static final String TABLE_NAME = "document";
    @Id
    private String id;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "entity_type")
    private String entityType;
    @Column(name = "entity_id")
    private String entityId;
    private String url;
    private String status;

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
