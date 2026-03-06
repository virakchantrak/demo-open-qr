package com.example.demoinvoicingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = MerchantEntity.TABLE_NAME)
public class MerchantEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "merchant";

    @Id
    private String id;

    @Column(name = "parent_company_id")
    private String parentCompanyId;

    @Column(name = "\"type\"")
    private String type;

    @Column(name = "referral_code", length = 100)
    private String referralCode;

    @Column(name = "business_name", length = 100)
    private String businessName;

    @Column(name = "year_of_establishment", length = 100)
    private String yearOfEstablishment;

    @Column(name = "tax_number", length = 255)
    private String taxNumber;

    @Column(name = "bank_account_number", length = 100)
    private String bankAccountNumber;

    @Column(name = "settlement_account", length = 100)
    private String settlementAccount;

    @Column(name = "wing_account_number", length = 100)
    private String wingAccountNumber;

    @Column(name = "\"name\"", length = 100)
    private String name;

    @Column(name = "owner_name", length = 100)
    private String ownerName;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 50)
    private String currency;

    @Column(length = 100)
    private String twitter;

    @Column(length = 100)
    private String facebook;

    @Column(length = 100)
    private String linkedin;

    @Column(length = 100)
    private String instagram;

    @Column(length = 100)
    private String website;

    @Column(length = 50)
    private String status;

    @Column(name = "wing_point")
    private Double wingPoint;

    private Boolean feature;

    @Column(length = 255)
    private String address;

    @Column(name = "\"desc\"", length = 100)
    private String desc;

    @Column(name = "back_account_name", length = 255)
    private String backAccountName;

    private Float rate;

    @Column(name = "create_from_portal")
    private Boolean createFromPortal;

    @Column(name = "bank_account_name", length = 255)
    private String bankAccountName;

    @Column(name = "is_wing_point")
    private Boolean isWingPoint;

    private String platform;

    @Column(name = "sale_represent_account")
    private String saleRepresentAccount;

    @Column(name = "owner_first_name", length = 255)
    private String ownerFirstName;

    @Column(name = "owner_last_name", length = 255)
    private String ownerLastName;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "is_buyer")
    private Boolean isBuyer;

    @Column(name = "delivery_metadata", columnDefinition = "TEXT")
    private String deliveryMetadata;

    @Column(name = "payment_metadata", columnDefinition = "TEXT")
    private String paymentMetadata;

    @Column(name = "business_name_kh", length = 255)
    private String businessNameKh;

    @Column(name = "wing_account_info", columnDefinition = "TEXT")
    private String wingAccountInfo;

    @Column(name = "allow_order_telegram_notification_for_buyer")
    private Boolean allowOrderTelegramNotificationForBuyer = false;

    @Column(name = "allow_order_telegram_notification")
    private Boolean allowOrderTelegramNotification = false;

    // === Audit fields ===
    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
