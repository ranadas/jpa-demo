package com.diee.jpademo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productCode;

    private String productName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productLine", referencedColumnName = "productLine")
    private ProductLine productLine;

    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    private Float buyPrice;
    @Column(name = "MSRP")
    private Float msrp;

    @OneToMany( mappedBy = "product")
    private Set<OrderDetail> orderDetails;
}

