package com.example.products.scheduler;

import com.example.products.dto.ProductDto;
import com.example.products.processor.ProductPostgresProcessor;
import com.example.products.processor.ProductRedisProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SyncData {

    private final ProductRedisProcessor productRedisProcessor;
    private final ProductPostgresProcessor productPostgresProcessor;

    @Scheduled(cron = "0 0/3 * 1/1 * ?")
    @Transactional
    public void moveToRedis() {
        log.info("Start running scheduler to update Redis model....");
        List<ProductDto> productsDb = getAllProductsDtoFromPostgres();
        deleteAllFromRedis();
        saveAllInRedis(productsDb);
    }

    private void saveAllInRedis(List<ProductDto> productsDb) {
        productRedisProcessor.saveAll(productsDb);
    }

    private List<ProductDto> getAllProductsDtoFromPostgres() {
        return productPostgresProcessor.findAll().stream()
                .map(productEntity -> ProductDto.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .message("Updated CQRS " + productEntity.getId())
                        .build())
                .toList();
    }

    private void deleteAllFromRedis() {
        productRedisProcessor.deleteAll();
    }
}
