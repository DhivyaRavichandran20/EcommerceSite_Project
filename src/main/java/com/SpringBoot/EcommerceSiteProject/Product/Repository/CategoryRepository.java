package com.SpringBoot.EcommerceSiteProject.Product.Repository;

import com.SpringBoot.EcommerceSiteProject.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
