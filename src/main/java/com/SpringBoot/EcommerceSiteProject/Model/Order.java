package com.SpringBoot.EcommerceSiteProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false)
    private User user;

    @Transient
    private Long tempsUserId;
    private Double orderTotal;
    private String orderStatus;
    private Double gstAmount ;
    private Double totalAmountWithGST ;

   /* @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false, updatable = false)
    private Date orderDate;

    @PrePersist
    protected void onCreate() {
        this.orderDate = new Date();
    }*/


    @JsonIgnore
    @OneToMany(mappedBy = "order",cascade = { CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();


}
