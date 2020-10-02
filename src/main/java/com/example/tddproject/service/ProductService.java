package com.example.tddproject.service;

import com.example.tddproject.model.Product;
import com.example.tddproject.repo.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepo productRepo;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product save(Product product) {
        product.setVersion(1);
        return productRepo.saveAndFlush(product);
    }

    @Override
    public boolean update(Product p) {
        if (productRepo.findById(p.getId()).isPresent()) {
            productRepo.saveAndFlush(p);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (productRepo.findById(id).isPresent()) {
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
