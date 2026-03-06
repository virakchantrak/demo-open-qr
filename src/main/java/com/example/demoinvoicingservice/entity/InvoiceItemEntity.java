package com.example.demoinvoicingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = InvoiceItemEntity.TABLE_NAME)
public class InvoiceItemEntity extends AuditEntity {
    private static final long serialVersionUID = 5527991405675934301L;

    public static final String TABLE_NAME = "invoice_item";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "invoice_id", nullable = false)
    private String invoiceId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "product")
    private String product;
    @Column(name = "product_qty", length = 11)
    private BigDecimal productQty;
    @Column(name = "product_unit_price", length = 21, precision = 19, scale = 2)
    private BigDecimal productUnitPrice;
    @Column(name = "currency")
    private String currency;
    @Column(name = "amount", length = 21, precision = 19, scale = 2)
    private BigDecimal amount;
    private String uom;
    @ManyToOne(targetEntity = InvoiceEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", insertable = false, updatable = false)
    private InvoiceEntity invoice;
}