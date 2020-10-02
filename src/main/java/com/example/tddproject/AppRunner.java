package com.example.tddproject;

import com.example.tddproject.model.Product;
import com.example.tddproject.repo.IProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private IProductRepo productRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

    @Override
    public void run(String... args) {
        String msg = "Saved Object : {}";

        LOGGER.info("----- Initializing Records -----");
        Product p1 = Product.builder()
                .name("P1")
                .quantity(5)
                .version(1)
                .build();
        productRepo.saveAndFlush(p1);
        LOGGER.info(msg, p1);

        Product p2 = Product.builder()
                .name("P2")
                .quantity(50)
                .version(1)
                .build();
        productRepo.saveAndFlush(p2);
        LOGGER.info(msg, p2);

        Product p3 = Product.builder()
                .name("P3")
                .quantity(500)
                .version(1)
                .build();
        productRepo.saveAndFlush(p3);
        LOGGER.info(msg, p3);
    }
}
