package com.rdas.entitygraph.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class OrderDetailDto {
    private String productCode;
    private Integer quantityOrdered;
    private Number priceEach;
    private Integer orderLineNumber;
}
