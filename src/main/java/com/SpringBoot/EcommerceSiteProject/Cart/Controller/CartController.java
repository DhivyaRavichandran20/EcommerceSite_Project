package com.SpringBoot.EcommerceSiteProject.Cart.Controller;

import com.SpringBoot.EcommerceSiteProject.Cart.Service.CartService;
import com.SpringBoot.EcommerceSiteProject.Model.Address;
import com.SpringBoot.EcommerceSiteProject.Model.Cart;
import com.SpringBoot.EcommerceSiteProject.Model.CartDTO;
import com.SpringBoot.EcommerceSiteProject.Model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cart")
public class CartController {

   @Autowired
    private CartService cartService;

    @PostMapping
    public String addToCart(@RequestBody CartDTO cartDTO) throws Exception {
        try {
            cartService.addToCart(cartDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "User does not exist", e);
        }

        return "success";
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Integer id) throws Exception {
        return cartService.getCartById(id);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Integer cartId, @RequestBody CartItem cartItem) throws Exception {
        return cartService.updateCart(cartId, cartItem);
    }

    @DeleteMapping("/{id},/{productId}")
    public void removeItemFromCart(@PathVariable Integer id, @PathVariable Integer productId) throws Exception {
        cartService.removeItemFromCart(id, productId);
    }


}
