package com.example.products.processor;

import com.example.products.dto.ProductDto;
import com.example.products.repository.ProductRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRedisProcessor {

    private final ProductRedisRepository productRedisRepository;

    public void manipulateAndSave(ProductDto productDto) {
        manipulateData(productDto);
        productRedisRepository.save(productDto);
    }

    private void manipulateData(ProductDto productDto) {
        productDto.setMessage("Hello CQRS " + productDto.getId());
        productDto.setPrice(productDto.getPrice() + 10);
    }

    public List<ProductDto> findAll() {
        List<ProductDto> productsDto = new ArrayList<>();
        productRedisRepository.findAll().forEach(productsDto::add);
        return productsDto;
    }

    public void saveAll(List<ProductDto> productsDto) {
        productRedisRepository.saveAll(productsDto);
    }

    public void deleteAll() {
        productRedisRepository.deleteAll();
    }
}
