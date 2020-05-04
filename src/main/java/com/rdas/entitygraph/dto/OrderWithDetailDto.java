package com.rdas.entitygraph.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class OrderWithDetailDto extends OrderDto {
    private List<OrderDetailDto> orderDetails;
}
