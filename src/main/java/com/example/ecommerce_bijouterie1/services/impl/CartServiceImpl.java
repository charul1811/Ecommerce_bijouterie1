package com.example.ecommerce_bijouterie1.services.impl;

import com.gmail.merikbest2015.ecommerce.domain.Bijoux;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repository.BijouxRepository;
import com.gmail.merikbest2015.ecommerce.service.CartService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final BijouxRepository bijouxRepository;

    @Override
    public List<Bijoux> getBijouxsInCart() {
        User user = userService.getAuthenticatedUser();
        return user.getBijouxList();
    }

    @Override
    @Transactional
    public void addBijouxToCart(Long bijouxId) {
        User user = userService.getAuthenticatedUser();
        Bijoux bijoux = bijouxRepository.getOne(bijouxId);
        user.getBijouxList().add(bijoux);
    }

    @Override
    @Transactional
    public void removeBijouxFromCart(Long bijouxId) {
        User user = userService.getAuthenticatedUser();
        Bijoux bijoux = bijouxRepository.getOne(bijouxId);
        user.getBijouxList().remove(bijoux);
    }
}
