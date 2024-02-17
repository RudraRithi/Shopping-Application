package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public String viewCart(@PathVariable Long userId, Model model) {
        Cart cart = cartService.getCartByUserId(userId);
        List<Product> availableProducts = productService.getAllProducts();
        model.addAttribute("cart", cart);
        model.addAttribute("availableProducts", availableProducts);
        return "cart";
    }

    @PostMapping("/addProduct")
    public String addProductToCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.addProductToCart(userId, productId);
        return "redirect:/cart/" + userId;
    }

    @GetMapping("/removeProduct")
    public String removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeProductFromCart(userId, productId);
        return "redirect:/cart/" + userId;
    }
}
