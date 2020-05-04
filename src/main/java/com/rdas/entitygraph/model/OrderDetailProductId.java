package com.rdas.entitygraph.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class OrderDetailProductId implements Serializable {

    @Column(name = "orderNumber")
    private Integer orderNumber;
    @Column(name = "productCode")
    private String productCode;

    public OrderDetailProductId() {
    }

    public OrderDetailProductId(Integer orderNumber, String productCode){
        this.orderNumber = orderNumber;
        this.productCode = productCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailProductId that = (OrderDetailProductId) o;
        return orderNumber.equals(that.orderNumber) &&
                productCode.equals(that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode);
    }
}
