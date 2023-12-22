package com.example.ecommerce_bijouterie1.services;


import com.example.ecommerce_bijouterie1.dto.request.PasswordResetRequest;
import com.example.ecommerce_bijouterie1.dto.response.MessageResponse;

public interface AuthenticationService {

    MessageResponse sendPasswordResetCode(String email);

    String getEmailByPasswordResetCode(String code);

    MessageResponse resetPassword(PasswordResetRequest request);
}
