package com.example.ecommerce_bijouterie1.repositories;


import com.example.ecommerce_bijouterie1.entities.Bijoux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BijouxRepository extends JpaRepository<Bijoux, Long> {

    List<Bijoux> findByIdIn(List<Long> bijouxsIds);

    Page<Bijoux> findAllByOrderByPriceAsc(Pageable pageable);

    @Query("SELECT bijoux FROM Bijoux bijoux WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'bijouxTitle' THEN UPPER(bijoux.bijouTitle) " +
            "   WHEN :searchType = 'country' THEN UPPER(bijoux.country) " +
            "   ELSE UPPER(bijoux.bijouMetal) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY bijoux.price ASC")
    Page<Bijoux> searchBijouxs(String searchType, String text, Pageable pageable);

    @Query("SELECT bijoux FROM Bijoux bijoux " +
            "WHERE (coalesce(:bijouxrs, null) IS NULL OR bijoux.bijouMetal IN :bijoux) " +
            "AND (coalesce(:genders, null) IS NULL OR bijoux.bijouType IN :type) " +
            "AND (coalesce(:priceStart, null) IS NULL OR bijoux.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY bijoux.price ASC")
    Page<Bijoux> getBijouxByFilterParams(
            List<String> bijouxMetal,
            List<String> types,
            Integer priceStart,
            Integer priceEnd,
            Pageable pageable);

}
