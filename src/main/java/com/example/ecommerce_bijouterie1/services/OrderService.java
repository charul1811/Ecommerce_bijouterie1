package com.example.ecommerce_bijouterie1.services;


import com.example.ecommerce_bijouterie1.dto.request.OrderRequest;
import com.example.ecommerce_bijouterie1.entities.Bijoux;
import com.example.ecommerce_bijouterie1.entities.Order;
import com.example.ecommerce_bijouterie1.entities.User;
import com.example.ecommerce_bijouterie1.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    List<Bijoux> getOrdering();

    Page<Order> getUserOrdersList(Pageable pageable);

    Long postOrder(User user, OrderRequest orderRequest);
    Order createOrder(List<Bijoux> bijoux);
}
