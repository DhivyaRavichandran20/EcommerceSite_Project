package com.SpringBoot.EcommerceSiteProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String userName;
    private  String email;
    private  String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    public User(){

    }

}
