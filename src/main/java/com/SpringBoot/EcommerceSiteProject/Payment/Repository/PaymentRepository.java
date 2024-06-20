package com.SpringBoot.EcommerceSiteProject.Payment.Repository;

import com.SpringBoot.EcommerceSiteProject.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
