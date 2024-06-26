package com.SpringBoot.EcommerceSiteProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="carts")
public class    Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable=false)
    private User user;

    @Transient
    private Long tempUserId;

    @JsonIgnore
    @OneToMany(mappedBy = "cart",cascade = { CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    private List<CartItem> cartItem = new ArrayList<>();

    private Double totalPrice = 0.0;

    private Double gstAmount = 0.0;

    private Double totalAmountWithGST = 0.0;


}
