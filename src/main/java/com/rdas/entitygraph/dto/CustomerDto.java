package com.rdas.entitygraph.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Integer customerNumber;
    private String customerName;
    private String phone;
    private String city;
    private String postalCode;
    private String country;
    private Number creditLimit;
}
