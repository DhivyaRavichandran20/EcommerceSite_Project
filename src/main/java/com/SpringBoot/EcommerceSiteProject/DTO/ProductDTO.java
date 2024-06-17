package com.SpringBoot.EcommerceSiteProject.DTO;


import com.SpringBoot.EcommerceSiteProject.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    Product product;

    private long temporaryUserId;
    private int tempCategoryId;


}




