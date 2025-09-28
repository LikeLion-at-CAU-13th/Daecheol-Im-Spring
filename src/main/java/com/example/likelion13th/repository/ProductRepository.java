package com.example.likelion13th.repository;

import com.example.likelion13th.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
