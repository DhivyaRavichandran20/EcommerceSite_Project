package com.SpringBoot.EcommerceSiteProject.DTO;

import com.SpringBoot.EcommerceSiteProject.Model.Cart;
import com.SpringBoot.EcommerceSiteProject.Model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

   Cart cart;

   CartItem cartItem;
}
