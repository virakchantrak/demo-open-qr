package com.example.demoinvoicingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = GatewaySettingEntity.TABLE_NAME)
public class GatewaySettingEntity extends AuditEntity {
    private static final long serialVersionUID = 1464054263267161966L;
    public static final String TABLE_NAME = "gateway_setting";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String gatewaySettingId;
    @Column(name = "third_party_id")
    private String thirdPartyId;
    @Column(name = "callback_url")
    private String callbackUrl;
    @Column(name = "payload")
    private String payload;
    @Column(name = "grant_type")
    private String grantType;
    @Column(name = "is_require_crc")
    private Boolean isRequireCrc;
    @Column(name = "is_require_transaction")
    private Boolean isRequireTransaction;
    @Column(name = "allow_double_transaction")
    private Boolean allowDoubleTransaction;
    @Column(name = "is_internal_service")
    private Boolean isInternalService;
    @Column(name = "crc_salt")
    private String crcSalt;
    @Column(name = "callback_auth_type")
    private String callbackAuthType;
    @Column(name = "callback_auth_data", columnDefinition = "TEXT")
    private String callbackAuthData;

}