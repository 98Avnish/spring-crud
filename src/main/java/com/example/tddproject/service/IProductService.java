package com.example.tddproject.service;

import com.example.tddproject.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    boolean update(Product product);

    boolean delete(Long id);

}
