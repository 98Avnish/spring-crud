package com.example.tddproject.repo;

import com.example.tddproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {

//    Optional<Product> findById(Integer id);
//
//    List<Product> findAll();
//
//    Product save(Product product);
//
//    boolean update(Product p);
//
//    boolean delete(Integer id);

}
