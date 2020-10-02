package com.example.tddproject.controller;

import com.example.tddproject.model.Product;
import com.example.tddproject.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return productService.findById(id).map(product -> {
            try {
                return ResponseEntity
                        .ok()
                        .eTag(Integer.toString(product.getVersion()))
                        .location(new URI("/product/" + product.getId()))
                        .body(product);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products")
    public Iterable<Product> getProducts() {
        return productService.findAll();
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        LOGGER.info("Creating new product :{}", product);
        Product newProduct = productService.save(product);
        try {
            return ResponseEntity
                    .created(new URI("/product/" + newProduct.getId()))
                    .eTag(Integer.toString(newProduct.getVersion()))
                    .body(newProduct);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product,
                                           @PathVariable Long id,
                                           @RequestHeader("If-Match") Integer ifMatch) {
        LOGGER.info("Updating product :{}", product);
        Optional<Product> existingProduct = productService.findById(id);

        return existingProduct.map(p -> {
           if (!p.getVersion().equals(ifMatch)) {
               return ResponseEntity.status(HttpStatus.CONFLICT).build();
           }

           p.setName(product.getName());
           p.setVersion(product.getVersion());
           p.setQuantity(product.getQuantity());

           LOGGER.info("Updating product :{}", p);

           try {
               if (productService.update(p)) {
                   return ResponseEntity
                           .ok()
                           .location(new URI("/product/" + p.getId()))
                           .eTag(Integer.toString(p.getVersion()))
                           .body(p);
               } else {
                   return ResponseEntity.notFound().build();
               }
           } catch (URISyntaxException e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
        }).orElse(ResponseEntity.notFound().build());
    }


}
