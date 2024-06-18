package com.SpringBoot.EcommerceSiteProject.Cart.Controller;

import com.SpringBoot.EcommerceSiteProject.Cart.Service.CartService;
import com.SpringBoot.EcommerceSiteProject.Model.Cart;
import com.SpringBoot.EcommerceSiteProject.DTO.CartDTO;
import com.SpringBoot.EcommerceSiteProject.Model.CartItem;
import com.SpringBoot.EcommerceSiteProject.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Cart updateCart(@PathVariable Integer id, @RequestBody CartItem cartItem) throws Exception {
        return cartService.updateCart(id, cartItem);
    }

   /* @DeleteMapping("/{id}")

    public ResponseEntity<String> removeItemFromCart(@PathVariable Integer id) throws Exception {
        cartService.removeItemFromCart(id);
        return ResponseEntity.ok("delete success");

    }*/
    @PostMapping("/checkout")
    public CartDTO createOrder(@RequestBody CartDTO cartDTO, @RequestParam Long userId) throws Exception {
        return cartService.createOrder(userId);
    }


}
