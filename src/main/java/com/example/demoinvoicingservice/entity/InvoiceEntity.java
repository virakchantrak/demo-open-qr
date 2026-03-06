package com.example.demoinvoicingservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = InvoiceEntity.TABLE_NAME)
public class InvoiceEntity extends AuditEntity {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "invoice";
    public static final String TABLE_SEQ_PREFIX = "INV";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "order_reference_no", nullable = false)
    private String orderReferenceNo;
    private String platform;
    @Column(name = "invoice_status")
    private String invoiceStatus;
    @Column(name = "currency")
    private String currency;
    @Column(name = "sub_total", length = 21, precision = 19, scale = 2, nullable = false)
    private BigDecimal subTotal;
    @Column(name = "discount", length = 21, precision = 19, scale = 2)
    private BigDecimal discount;
    @Column(name = "vat", length = 21, precision = 19, scale = 2)
    private BigDecimal vat;
    @Column(name = "service_charge", length = 21, precision = 19, scale = 2)
    private BigDecimal serviceCharge;
    @Column(name = "tax", length = 21, precision = 19, scale = 2)
    private BigDecimal tax;
    @Column(name = "total", length = 21, precision = 19, scale = 2)
    private BigDecimal total;
    @Column(name = "delivery_fee", length = 21, precision = 19, scale = 2)
    private BigDecimal deliveryFee;
    @Column(name = "type")
    private String type;
    @Column(name = "issue_date", length = 29)
    private Date issueDate;
    @Column(name = "invalidation_date", length = 29)
    private Date invalidationDate;
    @Column(name = "due_date", length = 29)
    private Date dueDate;
    @Column(name = "billing_from")
    private String billingFrom;
    @Column(name = "billing_to")
    private String billingTo;
    @Column(name = "tax_invoice_number")
    private String taxInvoiceNumber;
    @Column(name = "cml_invoice_number")
    private String cmlInvoiceNumber;
    @Column(name = "gen_invoice_number")
    private String genInvoiceNumber;
    @Column(name = "paid_status")
    private String paidStatus;
    @Column(name = "paid_currency")
    private String paidCurrency;
    @Column(name = "qr_code_url")
    private String qrCodeUrl;
    @Column(name = "qr_code_content")
    private String qrCodeContent;
    @Column(name = "paid_date", length = 29)
    private Date paidDate;
    @Column(name = "cancelled_by")
    private String cancelledBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cancelled_date")
    private Date cancelledDate;

    @Column(name = "merchant_id", nullable = false)
    private String merchantId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "merchant_id", insertable = false, updatable = false)
    private MerchantEntity merchant;

    @Column(name = "customer_id")
    private String customerId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;
    @Column(name = "gateway_setting_id")
    private String gatewaySettingId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "gateway_setting_id", insertable = false, updatable = false)
    private GatewaySettingEntity gatewaySetting;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = {CascadeType.ALL})
    private Set<InvoiceItemEntity> invoiceItems;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = {CascadeType.ALL})
    private Set<InvoicePaymentEntity> invoicePayments;
    @Column(name = "order_references", columnDefinition = "TEXT")
    private String orderReferences;
    private String description;
}