package com.example.demoinvoicingservice.web.vo.response;

import lombok.Data;

import java.util.Map;

@Data
public class CustomerResponseVO {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String companyName;
    private String tinNumber;
    private Map<String, Object> payload;
}
