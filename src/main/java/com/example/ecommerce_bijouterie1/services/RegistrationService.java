package com.example.ecommerce_bijouterie1.services;


import com.example.ecommerce_bijouterie1.dto.request.UserRequest;
import com.example.ecommerce_bijouterie1.dto.response.MessageResponse;

public interface RegistrationService {

    MessageResponse registration(String captchaResponse, UserRequest user);

    MessageResponse activateEmailCode(String code);
}
