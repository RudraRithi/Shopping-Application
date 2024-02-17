
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Cart;
import com.example.demo.model.Order;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void saveOrder(Order order);
    void deleteOrder(Long id);
    Order createOrderFromCart(Cart cart);
}
