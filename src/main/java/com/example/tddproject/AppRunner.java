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
        Product p1 = new Product();
        p1.setName("P1");
        p1.setQuantity(5);
        p1.setVersion(1);
        productRepo.saveAndFlush(p1);
        LOGGER.info(msg, p1);

        Product p2 = new Product();
        p2.setName("P2");
        p2.setQuantity(50);
        p2.setVersion(1);
        productRepo.saveAndFlush(p2);
        LOGGER.info(msg, p2);

        Product p3 = new Product();
        p3.setName("P3");
        p3.setQuantity(500);
        p3.setVersion(1);
        productRepo.saveAndFlush(p3);
        LOGGER.info(msg, p3);
    }
}
