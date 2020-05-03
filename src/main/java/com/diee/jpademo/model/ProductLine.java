package com.diee.jpademo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "productlines")
public class ProductLine implements Serializable {

    @Id
    private String productLine;
    private String textDescription;
    private String htmlDescription;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
}
