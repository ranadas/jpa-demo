package com.diee.jpademo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "orderdetails")
public class OrderDetail implements Serializable {

    @EmbeddedId
    private OrderDetailProductId id;

    @MapsId("orderNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderNumber")
    private Order order;

    @MapsId("productCode")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode", referencedColumnName = "productCode")
    private Product product;

    private Integer quantityOrdered;
    private BigDecimal priceEach;
    private Integer orderLineNumber;


    public OrderDetail(){
    }

    public OrderDetail(Order order, Product product){
        this.order = order;
        this.product = product;
        this.id = new OrderDetailProductId(order.getOrderNumber(), product.getProductCode());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return id.equals(that.id) &&
                order.equals(that.order) &&
                product.equals(that.product) &&
                quantityOrdered.equals(that.quantityOrdered) &&
                priceEach.equals(that.priceEach) &&
                orderLineNumber.equals(that.orderLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, quantityOrdered, priceEach, orderLineNumber);
    }
}
