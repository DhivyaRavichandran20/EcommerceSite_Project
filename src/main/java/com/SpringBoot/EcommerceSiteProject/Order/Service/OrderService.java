package com.SpringBoot.EcommerceSiteProject.Order.Service;

import com.SpringBoot.EcommerceSiteProject.Model.Cart;
import com.SpringBoot.EcommerceSiteProject.Model.CartItem;
import com.SpringBoot.EcommerceSiteProject.Model.Order;
import com.SpringBoot.EcommerceSiteProject.Model.OrderItem;
import com.SpringBoot.EcommerceSiteProject.Order.Repository.OrderItemRepository;
import com.SpringBoot.EcommerceSiteProject.Order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public void createOrder(Cart cart) {
        Order order = Order.builder().orderStatus("PENDING").orderTotal(cart.getTotalPrice())
                .createdDate(cart.getCreatedDate()).gstAmount(cart.getGstAmount())
                .totalAmountWithGST(cart.getTotalAmountWithGST()).user(cart.getUser()).build();

        orderRepository.save(order);

        //  Map Cart Items to Order Items
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem items : cart.getCartItem()) {
            OrderItem orderItem = OrderItem.builder().order(order).itemTotal(items.getItemTotal()).product(items.getProduct())
                    .quantity(items.getQuantity()).gstAmount(items.getGstAmount())
                    .productPrice(items.getProductPrice())
                    .build();

            orderItems.add(orderItem);
        }

        //  Save Order Items using order service
        orderItemRepository.saveAll(orderItems);

    }

    public void saveOrderItems(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);

    }

    //public void updateOrder(Order order) {
      //  orderRepository.save(order);
   // }
}
