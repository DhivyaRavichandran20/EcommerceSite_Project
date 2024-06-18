package com.SpringBoot.EcommerceSiteProject.Order.Repository;

import com.SpringBoot.EcommerceSiteProject.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
