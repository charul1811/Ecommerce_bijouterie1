package com.example.ecommerce_bijouterie1.services;


import com.example.ecommerce_bijouterie1.dto.request.BijouxRequest;
import com.example.ecommerce_bijouterie1.dto.request.SearchRequest;
import com.example.ecommerce_bijouterie1.dto.response.MessageResponse;
import com.example.ecommerce_bijouterie1.dto.response.UserInfoResponse;
import com.example.ecommerce_bijouterie1.entities.Bijoux;
import com.example.ecommerce_bijouterie1.entities.Order;
import com.example.ecommerce_bijouterie1.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    Page<Bijoux> getBijoux(Pageable pageable);

    Page<Bijoux> searchBijoux(SearchRequest request, Pageable pageable);

    Page<User> getUsers(Pageable pageable);

    Page<User> searchUsers(SearchRequest request, Pageable pageable);

    Object getOrder(Long orderId);

    Page<Order> getOrders(Pageable pageable);

    Page<Order> searchOrders(SearchRequest request, Pageable pageable);

    Bijoux getBijouxById(Long bijouxId);

    MessageResponse editBijoux(BijouxRequest bijouxRequest, MultipartFile file);

    MessageResponse addBijoux(BijouxRequest bijouxRequest, MultipartFile file);

    UserInfoResponse getUserById(Long userId, Pageable pageable);
}
