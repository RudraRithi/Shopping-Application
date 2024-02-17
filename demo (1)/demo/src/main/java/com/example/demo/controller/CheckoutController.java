package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/checkout")
    public String checkout(@RequestParam Long userId, Model model) {
        Cart cart = cartService.getCartByUserId(userId);

        if (cart == null || cart.getProducts().isEmpty()) {
            // Handle case where the cart is empty
            model.addAttribute("errorMessage", "Your cart is empty. Add products before checkout.");
            return "error";
        }

        // Assuming you have a method in OrderService to create an order from a cart
        orderService.createOrderFromCart(cart);

        // Clear the cart after checkout
        cartService.clearCart(userId);

        // You can add additional logic or messages as needed

        return "checkout-success"; // Redirect to a success page
    }
}
