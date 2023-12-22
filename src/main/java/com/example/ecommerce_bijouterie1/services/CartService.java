package com.example.ecommerce_bijouterie1.services;

import com.example.ecommerce_bijouterie1.entities.Bijoux;

import java.util.List;

public interface CartService {

    List<Bijoux> getPerfumesInCart();

    void addPerfumeToCart(Long perfumeId);

    void removePerfumeFromCart(Long perfumeId);
}
