package com.SpringBoot.EcommerceSiteProject.Payment.Service;

import com.SpringBoot.EcommerceSiteProject.Cart.Service.CartService;
import com.SpringBoot.EcommerceSiteProject.Model.Order;
import com.SpringBoot.EcommerceSiteProject.Model.Payment;
import com.SpringBoot.EcommerceSiteProject.Order.Service.OrderService;
import com.SpringBoot.EcommerceSiteProject.Payment.Repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.hibernate.type.OrderedSetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    public void completePayment(Long userId, Integer orderId) throws Exception {

        Order order = orderService.findOrder(userId,orderId);

        Payment payments = Payment.builder()
                .paymentMethod("VISA")
                .transactionId(String.valueOf(Math.random()))
                .totalAmountWithGST(order.getTotalAmountWithGST()).user(order.getUser())
                .order(order).build();

        paymentRepository.save(payments);

        orderService.completeOrder(order);

        cartService.deleteCart(userId);

        //delete cart
    }

}
