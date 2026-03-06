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
@Table(name = CustomerEntity.TABLE_NAME)
public class CustomerEntity extends AuditEntity {
    private static final long serialVersionUID = 1405206377414978960L;
    public static final String TABLE_NAME = "customer";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone", unique = true)
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "tin_number")
    private String tinNumber;

    @Column(name = "payload", columnDefinition = "TEXT")
    private String payload;

}