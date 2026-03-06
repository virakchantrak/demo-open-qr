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
@Table(name = InvoicePaymentEntity.TABLE_NAME)
public class InvoicePaymentEntity extends AuditEntity {
    private static final long serialVersionUID = 4889551463883334447L;

    public static final String TABLE_NAME = "invoice_payment";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "invoice_id", nullable = false)
    private String invoiceId;
    @Column(name = "bank_account")
    private String bankAccount;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "change_amount", length = 21, precision = 19, scale = 2)
    private BigDecimal changeAmount;
    @Column(name = "receive_amount", length = 21, precision = 19, scale = 2)
    private BigDecimal receiveAmount;
    @Column(name = "request_transaction_id")
    private String requestTransactionId;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_json", columnDefinition = "TEXT")
    private String paymentJson;
    @Column(name = "bank_account_name")
    private String bankAccountName;
    @Column(name = "hash_id")
    private String hashId;

    @ManyToOne(targetEntity = InvoiceEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", insertable = false, updatable = false)
    private InvoiceEntity invoice;

}