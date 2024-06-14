package com.SpringBoot.EcommerceSiteProject.Product.Repository;

import com.SpringBoot.EcommerceSiteProject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
