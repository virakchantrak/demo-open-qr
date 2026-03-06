package com.example.demoinvoicingservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Map;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerDTO {
	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String companyName;
	private String tinNumber;
	private Map<String, Object> payload;
}