package com.example.ecommerce_bijouterie1.dto.response;

import com.example.ecommerce_bijouterie1.entities.Order;
import com.example.ecommerce_bijouterie1.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data

public class UserInfoResponse {
    private User user;
    private Page<Order> orders;

    public UserInfoResponse(User user, Page<Order> orders) {
    }
}
