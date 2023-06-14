package com.example.products.controllers;

import com.example.products.dto.ProductDto;
import com.example.products.processor.ProductPostgresProcessor;
import com.example.products.processor.ProductRedisProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductPostgresProcessor productPostgresProcessor;
    private final ProductRedisProcessor productRedisProcessor;

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        return productPostgresProcessor.save(productDto);
    }

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productRedisProcessor.findAll();
    }
}
