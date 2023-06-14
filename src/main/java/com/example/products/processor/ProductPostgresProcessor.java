package com.example.products.processor;

import com.example.products.dto.ProductDto;
import com.example.products.model.ProductEntity;
import com.example.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPostgresProcessor {

    private final ProductRepository productRepository;
    private final ProductRedisProcessor productRedisProcessor;


    public ProductDto save(ProductDto productDto) {
        var newProduct = productRepository.save(ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build());
        productDto.setId(newProduct.getId());
        productRedisProcessor.manipulateAndSave(productDto);
        return productDto;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}
