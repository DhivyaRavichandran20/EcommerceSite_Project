package com.SpringBoot.EcommerceSiteProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name="Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String street;
    private  String city;
    private  String state;
    private  String country;
    private  String pincode;


    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Transient
    private Long userId;
}
