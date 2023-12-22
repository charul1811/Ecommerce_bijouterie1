package com.example.ecommerce_bijouterie1.services.impl;


import com.example.ecommerce_bijouterie1.constants.ErrorMessage;
import com.example.ecommerce_bijouterie1.dto.request.SearchRequest;
import com.example.ecommerce_bijouterie1.entities.Bijoux;
import com.example.ecommerce_bijouterie1.repositories.BijouxRepository;
import com.example.ecommerce_bijouterie1.services.BijouxService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BijouxServiceImpl implements BijouxService {

    private static final BijouxRepository bijouxRepository = null;
    //private final ModelMapper modelMapper;

    @Override
    public Bijoux getBijouxById(Long bijouxId) {
        return bijouxRepository.findById(bijouxId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.Bijoux_NOT_FOUND));
    }

    @Override
    public List<Bijoux> getPopularBijouxs() {
        List<Long> bijouxIds = Arrays.asList(26L, 43L, 46L, 106L, 34L, 76L, 82L, 85L, 27L, 39L, 79L, 86L);
        return bijouxRepository.findByIdIn(bijouxIds);
    }

    @Override
    public Page<Bijoux> getBijouxByFilterParams(SearchRequest searchRequest, Pageable pageable) {



        Integer startingPrice = searchRequest.getPrice();
        Integer endingPrice = startingPrice + (startingPrice == 0 ? 500 : 50);
        return bijouxRepository.getBijouxByFilterParams(
                searchRequest.bijouMetal,
                searchRequest.getBijouType(),
                startingPrice,
                endingPrice,
                pageable);

    }



    public Page<Bijoux> searchBijouxs(SearchRequest request, Pageable pageable) {
        return bijouxRepository.searchBijouxs(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Bijoux getBijouxByType(String bijouType) {
        return null;
    }
}
