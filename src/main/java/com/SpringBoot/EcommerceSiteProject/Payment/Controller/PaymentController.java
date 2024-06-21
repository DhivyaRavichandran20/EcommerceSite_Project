package com.SpringBoot.EcommerceSiteProject.Payment.Controller;

import com.SpringBoot.EcommerceSiteProject.Model.Category;
import com.SpringBoot.EcommerceSiteProject.Payment.Service.PaymentService;
import com.SpringBoot.EcommerceSiteProject.Product.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String createOrder(@RequestParam Long userId, @RequestParam Integer orderId) throws Exception {
        paymentService.completePayment(userId, orderId);
        return  "success";


    }
}

