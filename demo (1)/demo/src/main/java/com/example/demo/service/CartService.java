package com.example.demo.service;



import com.example.demo.model.Cart;

public interface CartService {
    Cart getCartByUserId(Long userId);
    void addProductToCart(Long userId, Long productId);
    void removeProductFromCart(Long userId, Long productId);
    void clearCart(Long userId); // Add this method
}
