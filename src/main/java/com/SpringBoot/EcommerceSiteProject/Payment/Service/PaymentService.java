package com.SpringBoot.EcommerceSiteProject.Payment.Service;

import com.SpringBoot.EcommerceSiteProject.Model.Order;
import com.SpringBoot.EcommerceSiteProject.Model.Payment;
import com.SpringBoot.EcommerceSiteProject.Payment.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void createPayment(Order order){

        Payment payments = Payment.builder()
                .paymentMethod("VISA")
                .totalAmountWithGST(order.getTotalAmountWithGST()).user(order.getUser())
                .order(order).build();

        paymentRepository.save(payments);
    }

}
