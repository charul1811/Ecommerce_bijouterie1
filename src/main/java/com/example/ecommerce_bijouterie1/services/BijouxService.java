package com.example.ecommerce_bijouterie1.services;


import com.example.ecommerce_bijouterie1.dto.request.SearchRequest;
import com.example.ecommerce_bijouterie1.entities.Bijoux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BijouxService {

    Bijoux getBijouxById(Long bijouxId);

    List<Bijoux> getPopularBijouxs();

    Page<Bijoux> getBijouxByFilterParams(SearchRequest searchRequest, Pageable pageable);

    Page<Bijoux> searchBijouxs(SearchRequest searchRequest, Pageable pageable);
    Bijoux getBijouxByType(String bijouType);
}
