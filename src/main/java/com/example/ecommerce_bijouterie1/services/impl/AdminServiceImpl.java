package com.example.ecommerce_bijouterie1.services.impl;


import com.example.ecommerce_bijouterie1.constants.SuccessMessage;
import com.example.ecommerce_bijouterie1.dto.request.BijouxRequest;
import com.example.ecommerce_bijouterie1.dto.request.SearchRequest;
import com.example.ecommerce_bijouterie1.dto.response.MessageResponse;
import com.example.ecommerce_bijouterie1.dto.response.UserInfoResponse;
import com.example.ecommerce_bijouterie1.entities.Bijoux;
import com.example.ecommerce_bijouterie1.entities.Order;
import com.example.ecommerce_bijouterie1.entities.User;
import com.example.ecommerce_bijouterie1.repositories.BijouxRepository;
import com.example.ecommerce_bijouterie1.repositories.OrderRepository;
import com.example.ecommerce_bijouterie1.repositories.UserRepository;
import com.example.ecommerce_bijouterie1.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;
    private final BijouxRepository bijouxRepository;
    private final OrderRepository orderRepository;
    private final Model modelMapper;

    @Override
    public Page<Bijoux> getBijoux(Pageable pageable) {
        return bijouxRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Bijoux> searchBijoux(SearchRequest request, Pageable pageable) {
        return bijouxRepository.searchBijouxs(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> searchUsers(SearchRequest request, Pageable pageable) {
        return userRepository.searchUsers(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Object getOrder(Long orderId) {
        return orderRepository.getById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);

    }

    @Override
    public Page<Order> searchOrders(SearchRequest request, Pageable pageable) {
        return orderRepository.searchOrders(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Bijoux getBijouxById(Long bijouxId) {
        return bijouxRepository.findById(bijouxId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.Bijoux_NOT_FOUND));
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse editBijoux(BijouxRequest bijouxRequest, MultipartFile file) {
        return saveBijoux(bijouxRequest, file, SuccessMessage.Bijoux_EDITED);
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse addBijoux(BijouxRequest bijouxRequest, MultipartFile file) {
        return saveBijoux(bijouxRequest, file, SuccessMessage.Bijoux_ADDED);
    }

    @Override
    public UserInfoResponse getUserById(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND));
        Page<Order> orders = orderRepository.findOrderByUserId(userId, pageable);
        return new UserInfoResponse(user, orders);
    }

    private MessageResponse saveBijoux(BijouxRequest bijouxRequest, MultipartFile file, String message) throws IOException {
        Bijoux bijoux = modelMapper.map(bijouxRequest, Bijoux.class);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            bijoux.setFilename(resultFilename);
        }
        bijouxRepository.save(bijoux);
        return new MessageResponse("alert-success", message);
    }
}
