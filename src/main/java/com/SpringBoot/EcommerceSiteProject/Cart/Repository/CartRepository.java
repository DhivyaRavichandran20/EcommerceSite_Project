package com.SpringBoot.EcommerceSiteProject.Cart.Repository;

import com.SpringBoot.EcommerceSiteProject.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    //Optional<Cart> findByUser(Long id);

    Optional<Cart> findByUserId(Long userId);
}
