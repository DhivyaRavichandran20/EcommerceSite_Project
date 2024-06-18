package com.SpringBoot.EcommerceSiteProject.Order.Repository;

import com.SpringBoot.EcommerceSiteProject.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
