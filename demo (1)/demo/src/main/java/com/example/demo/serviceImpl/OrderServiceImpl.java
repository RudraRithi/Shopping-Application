package com.example.demo.serviceImpl;



import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

        @Override
        public Order createOrderFromCart(Cart cart) {
            // Implement the logic to create an order from the cart
            // You can set other details of the order as needed
            Order order = new Order();
            order.setUser(cart.getUser());
            order.setProducts(cart.getProducts());
            order.setTotalAmount(calculateTotalAmount(cart.getProducts()));

            // Save the order to the repository
            orderRepository.save(order);

            return order;
        }

        // Add any other methods related to orders if needed

        private double calculateTotalAmount(List<Product> products) {
            double totalAmount = 0.0;

            for (Product product : products) {
                totalAmount += product.getPrice();
            }

            return totalAmount;
        }
    }


