package com.diee.jpademo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Integer customerNumber;
    private String customerName;
    private String phone;
    private String city;
    private String postalCode;
    private String country;
    private Number creditLimit;
}
