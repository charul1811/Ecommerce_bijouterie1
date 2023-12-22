package com.example.ecommerce_bijouterie1.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    public List<String> bijouMetal;
    private List<String> bijouTitle;
    private List<String> bijouType;
    private Integer price = 0;
    private String searchType;
    private String text;
}
