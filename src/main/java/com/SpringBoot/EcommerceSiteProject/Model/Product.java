package com.SpringBoot.EcommerceSiteProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer product_id;
    private  String product_Name;
    private  String description;
    private  double price;
    private double gst;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private Category category;

    @Transient
    private Integer categoryId;
}
