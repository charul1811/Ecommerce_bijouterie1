package com.example.ecommerce_bijouterie1.services.impl;


import com.example.ecommerce_bijouterie1.constants.ErrorMessage;
import com.example.ecommerce_bijouterie1.dto.request.OrderRequest;
import com.example.ecommerce_bijouterie1.entities.Bijoux;
import com.example.ecommerce_bijouterie1.entities.Order;
import com.example.ecommerce_bijouterie1.entities.User;
import com.example.ecommerce_bijouterie1.repositories.OrderRepository;
import com.example.ecommerce_bijouterie1.services.OrderService;
import com.example.ecommerce_bijouterie1.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final Model modelMapper;
    private final MailService mailService;

    @Override
    public Order getOrder(Long orderId) {
        User user = userService.getAuthenticatedUser();
        return orderRepository.getByIdAndUserId(orderId, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Bijoux> getOrdering() {
        User user = userService.getAuthenticatedUser();
        return user.getBijouxList();
    }

    @Override
    public Page<Order> getUserOrdersList(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        return orderRepository.findOrderByUserId(user.getId(), pageable);
    }




    @Override
    public Order createOrder(List<Bijoux> bijoux) {

        Order order=new Order();
        order.AddtoShoppingCart();
        return order;
    }

    @Override
    @Transactional
    public Long postOrder(User user, OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUser(user);
        order.getBijouxs().addAll(user.getBijouxList());
        orderRepository.save(order);
        user.getBijouxList().clear();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", order);
        mailService.sendMessageHtml(order.getEmail(), "Order #" + order.getId(), "order-template", attributes);
        return order.getId();
    }
}
