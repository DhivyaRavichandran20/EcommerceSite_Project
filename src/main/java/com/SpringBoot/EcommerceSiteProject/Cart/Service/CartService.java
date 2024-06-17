package com.SpringBoot.EcommerceSiteProject.Cart.Service;

import com.SpringBoot.EcommerceSiteProject.Cart.Repository.CartItemRepository;
import com.SpringBoot.EcommerceSiteProject.Cart.Repository.CartRepository;
import com.SpringBoot.EcommerceSiteProject.Model.*;
import com.SpringBoot.EcommerceSiteProject.Product.Repository.ProductRepository;
import com.SpringBoot.EcommerceSiteProject.User.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Transactional
    public void addToCart(CartDTO cartDTO) throws Exception {

        Long userId   = cartDTO.getCart().getTempUserId();

        User user = userRepository.findById(userId).orElseThrow( () -> new Exception("User does not exists"));

        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());

        cart.setUser(user);

        cart = cartRepository.save(cart);

        Product product = productRepository.findByUserId(userId).orElseThrow( () -> new Exception("Product does not exists"));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId()).orElse(new CartItem());

        int quantity = cartDTO.getCartItem().getQuantity();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setProductPrice(product.getPrice());

        double itemTotal = quantity * product.getPrice();
        double gstAmount = itemTotal * (product.getGstPercentage() / 100 ) ;
        cartItem.setItemTotal(itemTotal);
        cartItem.setGstAmount(gstAmount);

        cartItemRepository.save(cartItem);

        List<CartItem> cartItemList = cartItemRepository.findByCartId(cart.getId());

        double finalTotal = cartItemList.stream().mapToDouble(item -> item.getItemTotal()).sum();
        double gstTotal = cartItemList.stream().mapToDouble(item -> item.getGstAmount()).sum();
        double finalTotalWithGst = finalTotal + gstTotal;

        cart.setTotalPrice(finalTotal);
        cart.setGstAmount(gstTotal);
        cart.setTotalAmountWithGST(finalTotalWithGst);

        cartRepository.save(cart);


    }

    public Cart getCartById(Integer id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart updateCart(Integer cartId, CartItem cartItem) throws Exception {
        Cart cart = getCartById(cartId);
        Optional<CartItem> existingItem = cart.getCartItem().stream()
                .filter(item -> item.getTempProductId().equals(cartItem.getTempProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(cartItem.getQuantity());
        } else {
            cart.getCartItem().add(cartItem);
        }

        Product product = productRepository.findById(cartItem.getTempProductId()).orElseThrow(() -> new Exception("Product not found"));
        cartItem.setProduct(product);

        return cartRepository.save(cart);
    }

    public void removeItemFromCart(Integer id, Integer productId) throws Exception {

            Cart cart = getCartById(id);
            cart.getCartItem().removeIf(item -> item.getTempProductId().equals(productId));
            cartRepository.save(cart);
        }
    }
