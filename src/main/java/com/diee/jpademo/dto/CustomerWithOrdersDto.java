package com.diee.jpademo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CustomerWithOrdersDto extends CustomerDto {
    private List<OrderDto> orders;
}
